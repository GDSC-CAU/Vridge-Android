package com.gdsc_cau.vridge.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc_cau.vridge.ui.theme.Grey2
import com.gdsc_cau.vridge.ui.theme.White

@Composable
fun RecordScreen() {
    Column {
        RecordNavigator()
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
