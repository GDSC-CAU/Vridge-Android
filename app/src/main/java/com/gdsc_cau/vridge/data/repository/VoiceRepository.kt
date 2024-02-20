package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.models.Voice

interface VoiceRepository {
    fun getTrainingText(index: Int): String

    suspend fun makeVoice(path: String): Boolean

    suspend fun synthesize(voiceId: List<String>)

    suspend fun getVoiceList(): List<Voice>
}
