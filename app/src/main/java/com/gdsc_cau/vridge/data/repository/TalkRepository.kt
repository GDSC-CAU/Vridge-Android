package com.gdsc_cau.vridge.data.repository

interface TalkRepository {
    suspend fun getTts(text: String, uid: String, voiceId: String): String
}
