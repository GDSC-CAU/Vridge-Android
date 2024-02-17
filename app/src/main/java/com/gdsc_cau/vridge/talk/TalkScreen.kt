package com.gdsc_cau.vridge.talk

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

@Composable
fun TalkCard() {
    ElevatedCard(
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
        modifier =
            Modifier
                .fillMaxWidth()
                .height(40.dp)
    ) {
        Text(text = "Talk Card")
    }
}

@Composable
fun TalkInput(message: String, onClicked: () -> Unit) {
    Row {
        TextField(
            value = "",
            onValueChange = {}
        )
        IconButton(
            onClick = onClicked
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = ""
            )
        }
    }
}
