package com.gdsc_cau.vridge.voicelist

import androidx.compose.runtime.Composable
import com.gdsc_cau.vridge.main.Greeting

@Composable
fun VoiceListScreen(
    onVoiceClick: (String) -> Unit
) {
    Greeting(name = "VoiceListScreen")
}
