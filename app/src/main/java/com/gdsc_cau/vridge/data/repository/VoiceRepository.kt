package com.gdsc_cau.vridge.data.repository

interface VoiceRepository {
    fun getTrainingText(index: Int): String

    fun setTrainingVoice(index: Int, filePath: String): Boolean

    fun makeVoice(uid: String, path: String)

    fun synthesize(uid: String, voiceId: List<String>)

    fun getVoiceList(uid: String): List<String>
}
