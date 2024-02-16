package com.gdsc_cau.vridge.data.repository

import android.app.Application

class VoiceRepositoryImpl : VoiceRepository {
    private val assetManager = Application().assets
    private val scripts =
        assetManager.open("script.txt")
            .let {
                val size: Int = it.available()
                val buffer = ByteArray(size)
                it.read(buffer)
                String(buffer).split("\n")
            }

    override fun getTrainingText(index: Int): String {
        return scripts[index]
    }

    override fun setTrainingVoice(index: Int, filePath: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun makeVoice(uid: String, path: String) {
        TODO("Not yet implemented")
    }

    override fun synthesize(uid: String, voiceId: List<String>) {
        TODO("Not yet implemented")
    }

    override fun getVoiceList(uid: String): List<String> {
        TODO("Not yet implemented")
    }
}
