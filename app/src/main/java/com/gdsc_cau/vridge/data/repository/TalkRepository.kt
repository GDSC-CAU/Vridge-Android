package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.models.Tts

interface TalkRepository {
    suspend fun createTts(text: String, vid: String): String
    suspend fun getTtsUrl(vid: String, tid: String): String
    suspend fun getTalks(vid: String): List<Tts>
    suspend fun getTtsState(vid: String, tid: String): Boolean
}
