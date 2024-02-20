package com.gdsc_cau.vridge.data.repository

interface VoiceRepository {
    fun getTrainingText(index: Int): String

    suspend fun makeVoice(path: String): Boolean

    suspend fun synthesize(uid: String, voiceId: List<String>)

    suspend fun getVoiceList(uid: String): List<String>
}
