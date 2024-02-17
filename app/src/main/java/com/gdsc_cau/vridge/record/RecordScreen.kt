package com.gdsc_cau.vridge.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.ui.theme.Grey2
import com.gdsc_cau.vridge.ui.theme.Primary
import com.gdsc_cau.vridge.ui.theme.White

@Composable
fun RecordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RecordButton()
        RecordNavigator()
    }
}

@Composable
fun RecordButton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ElevatedButton(
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Primary,
                contentColor = White
            ),
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            shape = CircleShape,
            onClick = {
                // TODO: Record Start / Stop
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = "Record Button"
            )
        }
    }
}

@Composable
fun RecordNavigator() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RecordNavigateButton(text = "Record") {
            // TODO: Play Recorded
        }
        RecordNavigateButton(text = "Next") {
            // TODO: Next Record
        }
    }
}

@Composable
fun RecordNavigateButton(text: String, onBtnClicked: () -> Unit) {
    ElevatedButton(
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Grey2,
            contentColor = White
        ),
        modifier = Modifier
            .padding(all = 10.dp)
            .width(150.dp),
        onClick = onBtnClicked
    ) {
        Text(
            modifier = Modifier,
            fontSize = 20.sp,
            text = text,
        )
    }
}
