package com.gdsc_cau.vridge.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun VridgeTopBar(
    title: String,
    type: TopBarType,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .height(64.dp)
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (type != TopBarType.NONE) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = when (type) {
                        TopBarType.BACK -> Icons.Default.ArrowBack
                        TopBarType.CLOSE -> Icons.Default.Close
                        else -> Icons.Default.ArrowBack
                    },
                    contentDescription = null
                )
            }
        }
        Text(text = title, style = MaterialTheme.typography.headlineSmall)
    }
}

enum class TopBarType {
    BACK,
    CLOSE,
    NONE
}
