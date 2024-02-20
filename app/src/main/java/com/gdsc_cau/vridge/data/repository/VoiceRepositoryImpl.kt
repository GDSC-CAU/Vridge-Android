package com.gdsc_cau.vridge.data.repository

import android.content.Context
import android.net.Uri
import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.FileStorage
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.UUID
import javax.inject.Inject

class VoiceRepositoryImpl
    @Inject
    constructor(
        private val api: VridgeApi,
        private val storage: FileStorage,
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
                }

        override fun getTrainingText(index: Int): String {
            return scripts[index - 1]
        }

        override suspend fun makeVoice(path: String): Boolean {
            val uid = auth.currentUser?.uid ?: return false
            val vid = UUID.randomUUID().toString().replace("-", "")
            (1..33).forEach {
                val fileName = "$path/$it.m4a"
                val fileReader = FileInputStream(fileName)
                val data = ByteArray(fileReader.available())
                fileReader.read(data)
                storage.uploadFile(uid, vid, "$it.m4a", data)
            }

            val data = JsonObject(
                mapOf(
                    "uid" to JsonPrimitive(uid),
                    "vid" to JsonPrimitive(vid),
                    "path" to JsonPrimitive("$uid/$vid/train")
                )
            )

//            return api.uploadTrainingVoice(data)

            return true
        }

        override suspend fun synthesize(uid: String, voiceId: List<String>) {
            val data = JsonObject(
                mapOf(
                    "uid" to JsonPrimitive(uid),
                    "voiceId" to JsonArray(voiceId.map { JsonPrimitive(it) })
                )
            )

            api.synthesizeVoice(data)
        }

        override suspend fun getVoiceList(uid: String): List<String> {
            val data = JsonObject(
                mapOf(
                    "uid" to JsonPrimitive(uid)
                )
            )

            val result = api.getVoiceList(data)
            return result.voiceList
        }
    }
