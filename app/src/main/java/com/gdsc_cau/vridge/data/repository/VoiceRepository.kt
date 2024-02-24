package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.models.Voice
import kotlinx.coroutines.flow.Flow

interface VoiceRepository {
    fun getTrainingText(index: Int): String
    fun getScriptSize(): Int
    suspend fun beforeRecord(path: String): Boolean
    suspend fun saveVoice(index: Int): Boolean
    suspend fun afterRecord(name: String, pitch: Float): Boolean
    suspend fun synthesize(vid: List<String>): String
    suspend fun getVoiceList(): List<Voice>
}
