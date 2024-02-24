package com.gdsc_cau.vridge.ui.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gdsc_cau.vridge.ui.theme.OnPrimary
import com.gdsc_cau.vridge.ui.theme.Primary
import com.gdsc_cau.vridge.ui.theme.PrimaryDark
import com.gdsc_cau.vridge.ui.theme.PrimaryLight

@Composable
fun VoiceSettingDialog(
    isShowingDialog: Boolean,
    onConfirmRequest: () -> Unit,
    text: MutableState<String>,
    sliderPosition: MutableState<Float>,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = false
) {
    if (isShowingDialog) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            DialogContent(
                text = text,
                sliderPosition = sliderPosition,
                onConfirmRequest = onConfirmRequest,
                onDismissRequest = { return@DialogContent }
            )
        }
    }
}

@Composable
fun DialogContent(
    text: MutableState<String>,
    sliderPosition: MutableState<Float>,
    onConfirmRequest: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(400.dp)
            .wrapContentHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            label = {
                Text("Voice Name")
            }, value = text.value, onValueChange = { text.value = it })
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Man")
            Text("Woman")
        }
        Slider(
            modifier = Modifier.padding(horizontal = 16.dp),
            value = sliderPosition.value,
            onValueChange = { sliderPosition.value = it },
            colors = SliderDefaults.colors(
                thumbColor = PrimaryDark,
                activeTrackColor = PrimaryDark,
                inactiveTrackColor = PrimaryLight,
            ),
            steps = 19,
            valueRange = -15f..3f
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onDismissRequest, enabled = text.value != "") {
                Text("Cancel", color = OnPrimary)
            }
            Button(onClick = onConfirmRequest, modifier = Modifier.padding(start = 16.dp)) {
                Text("Confirm", color = OnPrimary)
            }
        }
    }
}
