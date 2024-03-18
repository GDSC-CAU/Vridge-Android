package com.gdsc_cau.vridge.ui.talk

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.data.models.Tts
import com.gdsc_cau.vridge.ui.theme.Black
import com.gdsc_cau.vridge.ui.theme.Grey3
import com.gdsc_cau.vridge.ui.theme.Grey4
import com.gdsc_cau.vridge.ui.theme.PrimaryUpperLight
import com.gdsc_cau.vridge.ui.theme.White
import com.gdsc_cau.vridge.ui.util.TopBarType
import com.gdsc_cau.vridge.ui.util.VridgeTopBar

enum class VoiceState {
    VOICE_LOADING,
    VOICE_PLAYING,
    VOICE_READY
}

@Composable
fun TalkScreen(
    voiceId: String,
    onBackClick: () -> Unit,
    viewModel: TalkViewModel = hiltViewModel()
) {
    viewModel.setVid(voiceId)

    val talks = viewModel.talks.collectAsStateWithLifecycle().value
    viewModel.getTalks()
    Box(modifier = Modifier.fillMaxSize()) {
        VridgeTopBar(title = "", type = TopBarType.BACK, onBackClick = onBackClick)
        TalkHistory(talks, viewModel)
        TalkInput(onSendClicked = {
            viewModel.createTts(it)
        })
    }
}

@Composable
fun TalkHistory(talks: List<Tts>, viewModel: TalkViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        items(items = talks) {
            TalkCard(it, viewModel)
        }
    }
}

@Composable
private fun TalkCard(talkData: Tts, viewModel: TalkViewModel) {
    val voiceState = when (
        viewModel.getTtsState(talkData.id).collectAsState(initial = VoiceState.VOICE_LOADING).value) {
        true -> VoiceState.VOICE_READY
        else -> VoiceState.VOICE_LOADING
    }

    ElevatedCard(
        colors =
        CardDefaults.cardColors(
            containerColor = White,
            contentColor = Black
        ),
        elevation =
        CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier =
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(all = 15.dp)
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier =
                Modifier
                    .padding(all = 15.dp)
                    .weight(1f)
            ) {
                Text(text = talkData.text)
            }
            TextCardController(voiceState = voiceState) { start ->
                viewModel.onPlay(start, talkData.id)
            }
        }
    }
}

@Composable
private fun TextCardController(voiceState: VoiceState, onPlay: (Boolean) -> Unit) {
    val playingStatus = remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
        Modifier
            .background(
                if (voiceState == VoiceState.VOICE_LOADING) {
                    Grey3
                } else {
                    PrimaryUpperLight
                }
            )
            .fillMaxHeight()
            .width(50.dp)
            .clickable {
                playingStatus.value = !playingStatus.value
                onPlay(playingStatus.value)
            }
    ) {
        Icon(
            painter =
            painterResource(
                if (playingStatus.value) {
                    R.drawable.ic_play_stop
                } else
                when (voiceState) {
                    VoiceState.VOICE_LOADING -> R.drawable.ic_downloading
                    VoiceState.VOICE_PLAYING -> R.drawable.ic_play_stop
                    else -> R.drawable.ic_play_arrow
                }
            ),
            contentDescription = "Play Button"
        )
    }
}

@Composable
fun TalkInput(onSendClicked: (String) -> Unit) {
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
            modifier =
            Modifier
                .background(Grey4)
                .height(60.dp)
                .width(60.dp)
        ) {
            IconButton(
                colors =
                IconButtonDefaults.iconButtonColors(
                    contentColor = Black
                ),
                modifier =
                Modifier
                    .height(40.dp)
                    .width(40.dp),
                onClick = {
                    onSendClicked(data)
                    data = ""
                }
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
        modifier =
        Modifier
            .background(Grey4)
            .padding(start = 10.dp)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier =
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(White)
                .fillMaxSize()
                .padding(start = 10.dp)
                .padding(vertical = 5.dp)
        ) {
            innerTextField()
        }
    }
}
