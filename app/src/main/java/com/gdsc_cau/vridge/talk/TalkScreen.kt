package com.gdsc_cau.vridge.talk

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gdsc_cau.vridge.ui.theme.Black
import com.gdsc_cau.vridge.ui.theme.White

private enum class VoiceState {
    VOICE_LOADING,
    VOICE_PLAYING,
    VOICE_READY
}

@Composable
fun TalkScreen(
    sessionId: String
) {
    TalkHistory()
    TalkInput {}
}

@Composable
fun TalkHistory() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = 55.dp)
                .verticalScroll(ScrollState(Int.MAX_VALUE)),
        verticalArrangement = Arrangement.Bottom
    ) {
        TalkCard(
            talkData = "This is Talk Card Content\nContent Ready",
            talkState = VoiceState.VOICE_READY
        )
        TalkCard(
            talkData = "This is Talk Card Content\nContent Playing",
            talkState = VoiceState.VOICE_PLAYING
        )
        TalkCard(
            talkData = "This is Talk Card Content\nContent Loading",
            talkState = VoiceState.VOICE_LOADING
        )
    }
}

@Composable
private fun TalkCard(talkData: String, talkState: VoiceState) {
    ElevatedCard(
        colors =
            CardDefaults.cardColors(
                containerColor = White,
                contentColor = Black
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
            text = talkData
        )
    }
}

@Composable
fun TalkInput(onClicked: () -> Unit) {
    var data by remember {
        mutableStateOf("")
    }

    Row(
        modifier =
            Modifier
                .fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        BasicTextField(
            modifier =
                Modifier
                    .height(50.dp)
                    .weight(1f),
            value = data,
            onValueChange = { input ->
                data = input
            },
            decorationBox = { innerTextField ->
                TalkInputDecor(innerTextField)
            }
        )
        IconButton(
            modifier =
                Modifier
                    .height(45.dp)
                    .padding(all = 5.dp)
                    .width(45.dp),
            onClick = onClicked
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Send Button"
            )
        }
    }
}

@Composable
fun TalkInputDecor(innerTextField: @Composable () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedCard(
            colors =
                CardDefaults.cardColors(
                    containerColor = White,
                    contentColor = Black
                ),
            modifier =
                Modifier
                    .background(White)
                    .fillMaxSize()
                    .padding(all = 5.dp)
        ) {
            innerTextField()
        }
    }
}
