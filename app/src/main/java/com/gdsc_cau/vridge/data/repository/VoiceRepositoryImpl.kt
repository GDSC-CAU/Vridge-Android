package com.gdsc_cau.vridge.data.repository

import android.app.Application
import com.gdsc_cau.vridge.data.api.VridgeApi
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject

class VoiceRepositoryImpl
    @Inject
    constructor(
        private val api: VridgeApi
    ) : VoiceRepository {
        private val assetManager = Application().assets
        private val scripts =
            assetManager.open("script.txt")
                .let {
                    val size: Int = it.available()
                    val buffer = ByteArray(size)
                    it.read(buffer)
                    String(buffer).split("\n")
                }

        override fun getTrainingText(index: Int): String {
            return scripts[index]
        }

        override suspend fun setTrainingVoice(index: Int, filePath: String): Boolean {
            TODO("Upload file to firebase storage")
        }

        override suspend fun makeVoice(uid: String, path: String) {
            val data = JsonObject(
                mapOf(
                    "uid" to JsonPrimitive(uid),
                    "path" to JsonPrimitive(path)
                )
            )

            api.uploadTrainingVoice(data)
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
