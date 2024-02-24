package com.gdsc_cau.vridge.ui.record

import android.media.MediaRecorder
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.ui.main.MainNavigator
import com.gdsc_cau.vridge.ui.theme.Black
import com.gdsc_cau.vridge.ui.theme.Grey2
import com.gdsc_cau.vridge.ui.theme.Grey4
import com.gdsc_cau.vridge.ui.theme.Primary
import com.gdsc_cau.vridge.ui.theme.White
import com.gdsc_cau.vridge.ui.util.LoadingDialog

@Composable
fun RecordScreen(navHostController: MainNavigator, viewModel: RecordViewModel = hiltViewModel()) {
    val index = viewModel.recordIndex.collectAsStateWithLifecycle().value
    val text = viewModel.recordText.collectAsStateWithLifecycle().value
    val isRecorded = viewModel.isRecorded.collectAsStateWithLifecycle().value
    val finished = viewModel.finished.collectAsStateWithLifecycle().value
    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle().value

    val fileName = LocalContext.current.externalCacheDir?.absolutePath ?: ""

    val recordingStatus = rememberSaveable {
        mutableStateOf(false)
    }

    val playingStatus = rememberSaveable {
        mutableStateOf(false)
    }

    val voiceName = remember { mutableStateOf("") }
    val sliderPosition = remember { mutableFloatStateOf(-6f) }

    val recorder = if (Build.VERSION_CODES.S <= Build.VERSION.SDK_INT) {
        MediaRecorder(LocalContext.current)
    } else {
        MediaRecorder()
    }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
    ) {
        RecordDataView(idx = if (index <= viewModel.scriptSize) "$index / ${viewModel.scriptSize}" else "", data = text)
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            RecordButton(recordingStatus) {
                viewModel.onRecord(it, recorder)

            }
        }
        RecordNavigator(
            playingStatus,
            isRecorded,
            index == viewModel.scriptSize,
            { viewModel.onPlay(it) }
        ) {
            viewModel.getNextText()
        }
        LoadingDialog(isLoading)
        VoiceSettingDialog(
            isShowingDialog = (index == viewModel.scriptSize + 1),
            text = voiceName,
            sliderPosition = sliderPosition,
            onConfirmRequest = {
                viewModel.confirmVoice(voiceName.value, sliderPosition.floatValue)
            }
        )
    }

    LaunchedEffect(key1 = fileName) {
        viewModel.setFileName(fileName)
    }

    LaunchedEffect(key1 = finished) {
        if (finished) {
            navHostController.popBackStack()
        }
    }
}

@Composable
fun RecordDataView(idx: String, data: String) {
    Column(
        modifier = Modifier
    ) {
        RecordDataIndex(idx = idx)
        RecordDataCard(data = data)
    }
}

@Composable
fun RecordDataIndex(idx: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text(
            fontSize = 25.sp,
            color = Black,
            text = idx
        )
    }
}

@Composable
fun RecordDataCard(data: String) {
    ElevatedCard(
        colors =
        CardDefaults.elevatedCardColors(
            containerColor = Grey4,
            contentColor = Black
        ),
        elevation =
        CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier =
        Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(all = 30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Text(
                fontSize = 20.sp,
                text = data,
                softWrap = true,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun RecordButton(recordingStatus: MutableState<Boolean>, onClickRecord: (Boolean) -> Unit) {
    val lottieAnimatable = rememberLottieAnimatable()
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_lottie_loading)
    )

    LaunchedEffect(recordingStatus) {
        lottieAnimatable.animate(
            composition = lottieComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
        Modifier
            .fillMaxSize()
    ) {
        ElevatedButton(
            colors =
            ButtonDefaults.elevatedButtonColors(
                containerColor = Primary,
                contentColor = White
            ),
            modifier =
            Modifier
                .height(130.dp)
                .width(130.dp),
            shape = CircleShape,
            onClick = {
                recordingStatus.value = !recordingStatus.value
                onClickRecord(recordingStatus.value)
            }
        ) {
            if (recordingStatus.value) {
                LottieAnimation(
                    composition = lottieComposition,
                    contentScale = ContentScale.FillHeight,
                    iterations = LottieConstants.IterateForever
                )
            } else {
                Icon(
                    painter =
                    painterResource(
                        id = R.drawable.ic_mic
                    ),
                    contentDescription = "Record Button"
                )
            }
        }
    }
}

@Composable
fun RecordNavigator(
    playingStatus: MutableState<Boolean>,
    clickable: Boolean,
    isFinish: Boolean,
    onClickPlay: (Boolean) -> Unit,
    onClickNext: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier =
        Modifier
            .fillMaxWidth()
    ) {
        RecordNavigateButton(
            text = if (!playingStatus.value) stringResource(id = R.string.record_btn_play) else stringResource(id = R.string.record_btn_stop),
            clickable
        ) {
            playingStatus.value = !playingStatus.value
            onClickPlay(playingStatus.value)
        }
        RecordNavigateButton(
            text = if (isFinish) stringResource(id = R.string.record_btn_finish) else stringResource(id = R.string.record_btn_next),
            clickable
        ) {
            onClickNext()
        }
    }
}

@Composable
fun RecordNavigateButton(text: String, clickable: Boolean, onBtnClicked: () -> Unit) {
    ElevatedButton(
        enabled = true,
        colors =
        ButtonDefaults.elevatedButtonColors(
            containerColor = if (clickable) Primary else Grey2,
            contentColor = White
        ),
        modifier =
        Modifier
            .padding(all = 20.dp)
            .width(150.dp),
        onClick = onBtnClicked
    ) {
        Text(
            modifier = Modifier,
            fontSize = 20.sp,
            text = text
        )
    }
}
