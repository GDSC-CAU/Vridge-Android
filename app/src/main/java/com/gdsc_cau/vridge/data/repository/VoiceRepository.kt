package com.gdsc_cau.vridge.data.repository

interface VoiceRepository {
    fun getTrainingText(index: Int): String

    suspend fun setTrainingVoice(index: Int, filePath: String): Boolean

    suspend fun makeVoice(uid: String, path: String)

    suspend fun synthesize(uid: String, voiceId: List<String>)

    suspend fun getVoiceList(uid: String): List<String>
}
