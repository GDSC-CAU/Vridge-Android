package com.gdsc_cau.vridge.talk

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.ui.theme.Black
import com.gdsc_cau.vridge.ui.theme.Grey3
import com.gdsc_cau.vridge.ui.theme.PrimaryUpperLight
import com.gdsc_cau.vridge.ui.theme.White

private enum class VoiceState {
    VOICE_LOADING,
    VOICE_PLAYING,
    VOICE_READY
}

private val dummyTalkTextData: Array<String> = arrayOf(
    "This is Talk Card Content\nThis is Talk Card Content\nThis is Talk Card Content\nContent Ready",
    "This is Talk Card Content\nThis is Talk Card Content\nContent Playing",
    "This is Talk Card Content\nContent Loading"
)

private val dummyTalkStateData: Array<VoiceState> = arrayOf(
    VoiceState.VOICE_READY,
    VoiceState.VOICE_PLAYING,
    VoiceState.VOICE_LOADING
)

@Composable
fun TalkScreen(
    sessionId: String
) {
    TalkHistory()
    TalkInput(onSendClicked = {})
}

@Composable
fun TalkHistory() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp)
            .verticalScroll(ScrollState(Int.MAX_VALUE)),
        verticalArrangement = Arrangement.Bottom
    ) {
        TalkCard(talkData = dummyTalkTextData[0], voiceState = dummyTalkStateData[0])
        TalkCard(talkData = dummyTalkTextData[1], voiceState = dummyTalkStateData[1])
        TalkCard(talkData = dummyTalkTextData[2], voiceState = dummyTalkStateData[2])
        TalkCard(talkData = dummyTalkTextData[0], voiceState = dummyTalkStateData[0])
        TalkCard(talkData = dummyTalkTextData[1], voiceState = dummyTalkStateData[1])
        TalkCard(talkData = dummyTalkTextData[2], voiceState = dummyTalkStateData[2])
    }
}

@Composable
private fun TalkCard(talkData: String, voiceState: VoiceState) {
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
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(all = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(all = 15.dp)
                    .weight(1f),
            ) {
                Text(text = talkData)
            }
            TextCardController(voiceState = voiceState)
        }
    }
}

@Composable
private fun TextCardController(voiceState: VoiceState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                if (voiceState == VoiceState.VOICE_LOADING) Grey3
                else PrimaryUpperLight
            )
            .fillMaxHeight()
            .width(50.dp)
            .clickable {
                // TODO: Call Control Function
            }
    ) {
        Icon(
            painter =
                painterResource(
                    when (voiceState) {
                        VoiceState.VOICE_LOADING -> R.drawable.ic_downloading
                        VoiceState.VOICE_PLAYING -> R.drawable.ic_play_stop
                        else -> R.drawable.ic_play_arrow
                    },
                ),
            contentDescription = "Play Button"
        )
    }
}

@Composable
fun TalkInput(onSendClicked: () -> Unit) {
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
            modifier = Modifier
                .height(60.dp)
                .weight(1f),
            value = data,
            onValueChange = { input ->
                data = input
            },
            decorationBox = { innerTextField ->
                TalkInputDecor(innerTextField)
            }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
        ) {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Grey3,
                    contentColor = Black
                ),
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp),
                onClick = onSendClicked
            ) {
                Icon(
                    painterResource(R.drawable.ic_send),
                    contentDescription = "Send Button"
                )
            }
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
            modifier = Modifier
                .background(White)
                .fillMaxSize()
                .padding(all = 5.dp)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            ) {
                innerTextField()
            }
        }
    }
}
