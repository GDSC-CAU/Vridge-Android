package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.FileStorage
import com.gdsc_cau.vridge.data.database.InfoDatabase
import com.gdsc_cau.vridge.data.models.Tts
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject

class TalkRepositoryImpl
@Inject
constructor(
    private val api: VridgeApi,
    private val auth: FirebaseAuth,
    private val storage: FileStorage,
    private val database: InfoDatabase
) : TalkRepository {
    override suspend fun createTts(text: String, uid: String, voiceId: String): String {
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

    override suspend fun getTtsUrl(vid: String, ttsId: String): String {
        val uid = auth.currentUser?.uid ?: return ""
        return storage.getDownloadUrl("$uid/$vid/$ttsId.wav")
    }

    override suspend fun getTalks(vid: String): List<Tts> {
        val uid = auth.currentUser?.uid ?: return emptyList()
        return database.getTalks(uid, vid)
    }

    override suspend fun getTtsState(vid: String, ttsId: String): Boolean {
        val uid = auth.currentUser?.uid ?: return false
        return storage.existTts(uid, vid, ttsId)
    }
}
