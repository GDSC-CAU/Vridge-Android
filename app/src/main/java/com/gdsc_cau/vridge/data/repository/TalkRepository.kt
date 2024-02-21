package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.models.Tts

interface TalkRepository {
    suspend fun createTts(text: String, uid: String, voiceId: String): String
    suspend fun getTtsUrl(vid: String, ttsId: String): String
    suspend fun getTalks(vid: String): List<Tts>
    suspend fun getTtsState(vid: String, ttsId: String): Boolean
}
