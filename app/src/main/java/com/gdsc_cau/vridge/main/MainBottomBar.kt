package com.gdsc_cau.vridge.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MainBottomBar(
    visible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter =
            fadeIn(
                animationSpec =
                    keyframes {
                        this.durationMillis = 0
                    }
            ),
        exit =
            fadeOut(
                animationSpec =
                    keyframes {
                        this.durationMillis = 0
                    }
            )
    ) {
        Row(
            modifier =
                Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface
                    ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEach {
                MainBottomBarItem(
                    tab = it,
                    selected = currentTab == it,
                    onTabSelected = { onTabSelected(it) }
                )
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onTabSelected: () -> Unit
) {
    Column(
        modifier =
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .selectable(
                    selected = selected,
                    indication = null,
                    onClick = onTabSelected,
                    role = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = tab.iconResId),
            contentDescription = tab.contentDescription,
            tint =
                if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onBackground
                },
            modifier = Modifier.size(34.dp)
        )
        Text(text = tab.name, style = MaterialTheme.typography.labelSmall)
    }
}
