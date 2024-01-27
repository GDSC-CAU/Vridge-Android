package com.gdsc_cau.vridge.voicelist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import com.gdsc_cau.vridge.main.Greeting

@Composable
fun VoiceListScreen(
    padding: PaddingValues,
    onVoiceClick: (String) -> Unit
) {
    Button(onClick = { onVoiceClick("1") }) {
        Greeting(name = "VoiceListScreen")
    }
}
