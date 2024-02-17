package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.FileStorage
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject

class TalkRepositoryImpl
    @Inject
    constructor(
        private val api: VridgeApi,
        private val storage: FileStorage
    ) : TalkRepository {
    override suspend fun getTts(text: String, uid: String, voiceId: String): String {
            val data = JsonObject(
                mapOf(
                    "text" to JsonPrimitive(text),
                    "uid" to JsonPrimitive(uid),
                    "voiceId" to JsonPrimitive(voiceId)
                )
            )

            val result = api.createTts(data)
            TODO("File Download")
        }
    }
