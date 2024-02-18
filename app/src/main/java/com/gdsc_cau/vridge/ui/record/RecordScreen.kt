package com.gdsc_cau.vridge.ui.record

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.ui.theme.Black
import com.gdsc_cau.vridge.ui.theme.Grey2
import com.gdsc_cau.vridge.ui.theme.Grey4
import com.gdsc_cau.vridge.ui.theme.Primary
import com.gdsc_cau.vridge.ui.theme.White

@Composable
fun RecordScreen() {
    val recordingStatus =
        rememberSaveable {
            mutableStateOf(false)
        }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
    ) {
        RecordDataView(idx = 0, data = "Hello, World!\nHello, World!")
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
        ) {
            RecordButton(recordingStatus)
        }
        RecordNavigator()
    }
}

@Composable
fun RecordDataView(idx: Int, data: String) {
    Column(
        modifier = Modifier
    ) {
        RecordDataIndex(idx = idx)
        RecordDataCard(data = data)
    }
}

@Composable
fun RecordDataIndex(idx: Int) {
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
            text = "$idx / 33"
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
                text = data
            )
        }
    }
}

@Composable
fun RecordButton(recordingStatus: MutableState<Boolean>) {
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
                // TODO: Record Start / Stop
                recordingStatus.value = !recordingStatus.value
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
fun RecordNavigator() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier =
            Modifier
                .fillMaxWidth()
    ) {
        RecordNavigateButton(text = stringResource(id = R.string.record_btn_play)) {
            // TODO: Play Recorded
        }
        RecordNavigateButton(text = stringResource(id = R.string.record_btn_next)) {
            // TODO: Next Record
        }
    }
}

@Composable
fun RecordNavigateButton(text: String, onBtnClicked: () -> Unit) {
    ElevatedButton(
        colors =
            ButtonDefaults.elevatedButtonColors(
                containerColor = Grey2,
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
