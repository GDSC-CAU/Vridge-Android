package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.FileStorage
import com.gdsc_cau.vridge.data.database.InfoDatabase
import com.gdsc_cau.vridge.data.models.Tts
import com.gdsc_cau.vridge.data.dto.TtsDTO
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID
import javax.inject.Inject

class DefaultTalkRepository
@Inject
constructor(
    private val api: VridgeApi,
    private val auth: FirebaseAuth,
    private val storage: FileStorage,
    private val database: InfoDatabase
) : TalkRepository {
    override suspend fun createTts(text: String, vid: String): String {
        val uid = getUid() ?: return ""
        val tid = UUID.randomUUID().toString().replace("-", "")
        val data = TtsDTO(text, uid, vid, tid, 0)

        if (api.createTts(data).success.not()) return ""
        database.saveTts(uid, vid, Tts(tid, text, System.currentTimeMillis()))

        return tid
    }

    override suspend fun getTtsUrl(vid: String, tid: String): String {
        val uid = getUid() ?: return ""
        return storage.getDownloadUrl("$uid/$vid/$tid.wav")
    }

    override suspend fun getTalks(vid: String): List<Tts> {
        val uid = getUid() ?: return emptyList()
        return database.getTalks(uid, vid)
    }

    override suspend fun getTtsState(vid: String, tid: String): Boolean {
        val uid = getUid() ?: return false
        return storage.existTts(uid, vid, tid)
    }

    private fun getUid() = auth.currentUser?.uid
}
