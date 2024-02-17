package com.gdsc_cau.vridge.talk

import androidx.compose.runtime.Composable
import com.gdsc_cau.vridge.main.Greeting

private enum class VoiceState {
    VOICE_LOADING,
    VOICE_PLAYING,
    VOICE_READY
}

@Composable
fun TalkScreen(
    sessionId: String
) {
    Greeting(name = "Talk $sessionId")
}
