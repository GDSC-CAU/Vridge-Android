package com.gdsc_cau.vridge.data.repository

import android.content.Context
import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.FileStorage
import com.gdsc_cau.vridge.data.database.InfoDatabase
import com.gdsc_cau.vridge.data.models.Voice
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.io.FileInputStream
import java.util.UUID
import javax.inject.Inject

class DefaultVoiceRepository
    @Inject
    constructor(
        private val api: VridgeApi,
        private val storage: FileStorage,
        private val database: InfoDatabase,
        private val auth: FirebaseAuth,
        @ApplicationContext private val context: Context
    ) : VoiceRepository {
        private val assetManager = context.assets
        private val scripts =
            assetManager.open("script.txt")
                .let {
                    val size: Int = it.available()
                    val buffer = ByteArray(size)
                    it.read(buffer)
                    String(buffer).split("\n")
                }.filter { it.isNotBlank() }

        private var vid: String? = null
        private var path: String? = null

        override fun getTrainingText(index: Int): String {
            if (index < 1 || index > scripts.size)
                return ""
            return scripts[index - 1]
        }

        override fun getScriptSize(): Int {
            return scripts.size
        }

        override suspend fun beforeRecord(path: String): Boolean {
            vid = UUID.randomUUID().toString().replace("-", "")
            this.path = path
            return true
        }

        override suspend fun saveVoice(index: Int): Boolean {
            val uid = auth.currentUser?.uid ?: return false
            val vid = this.vid ?: return false
            val fileName = "$path/$index.m4a"
            val fileReader = FileInputStream(fileName)
            val data = ByteArray(fileReader.available())
            fileReader.read(data)
            return storage.uploadFile(uid, vid, "$index.m4a", data)
        }

        override suspend fun afterRecord(name: String, pitch: Float): Boolean {
            val uid = auth.currentUser?.uid ?: return false
            val vid = this.vid ?: return false
            val data = JsonObject(
                mapOf(
                    "uid" to JsonPrimitive(uid),
                    "vid" to JsonPrimitive(vid),
                    "path" to JsonPrimitive("$uid/$vid/train")
                )
            )
            database.saveVoice(uid, vid, name, pitch)
            return api.uploadTrainingVoice(data)
        }

        override suspend fun synthesize(vid: List<String>): String {
            val uid = auth.currentUser?.uid ?: return ""

            val data = JsonObject(
                mapOf(
                    "uid" to JsonPrimitive(uid),
                    "vids" to JsonArray( vid.map { JsonPrimitive(it) } )
                )
            )

            return api.synthesizeVoice(data)
        }

        override suspend fun getVoiceList(): List<Voice> {
            val uid = auth.currentUser?.uid ?: return emptyList()

            val result = database.getVoiceList(uid).map { (vid, name) ->
                Voice(vid, name)
            }

            return result
        }
    }
