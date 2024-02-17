package com.gdsc_cau.vridge.talk

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private enum class VoiceState {
    VOICE_LOADING,
    VOICE_PLAYING,
    VOICE_READY
}

@Composable
fun TalkScreen(
    sessionId: String
) {
    Column {
        TalkCard()
        TalkInput {}
    }
}

@Composable
fun TalkCard() {
    ElevatedCard(
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(all = 15.dp)
    ) {
        Text(
            modifier =
                Modifier
                    .padding(all = 15.dp),
            text = "This is Talk Card Content"
        )
    }
}

@Composable
fun TalkInput(onClicked: () -> Unit) {
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
