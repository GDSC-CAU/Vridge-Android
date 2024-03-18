package com.gdsc_cau.vridge.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gdsc_cau.vridge.ui.profile.ProfileRoute
import com.gdsc_cau.vridge.ui.profile.ProfileScreen
import com.gdsc_cau.vridge.ui.record.RecordRoute
import com.gdsc_cau.vridge.ui.record.RecordScreen
import com.gdsc_cau.vridge.ui.talk.TalkRoute
import com.gdsc_cau.vridge.ui.talk.TalkScreen
import com.gdsc_cau.vridge.ui.util.VridgeTopBar
import com.gdsc_cau.vridge.ui.voicelist.VoiceListRoute
import com.gdsc_cau.vridge.ui.voicelist.VoiceListScreen

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        content = { padding ->
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(padding)
                        .padding(vertical = 8.dp)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination
                ) {
                    composable(VoiceListRoute.route) {
                        VoiceListScreen(
                            padding = padding,
                            onRecordClick = { navigator.navigateRecord() },
                            onVoiceClick = { navigator.navigateTalk(it.id) }
                        )
                    }
                    composable(RecordRoute.route) {
                        RecordScreen(onBackClick = { navigator.popBackStackIfNotHome() })
                    }
                    composable(
                        route = TalkRoute.detailRoute("{id}"),
                        arguments =
                            listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                }
                            )
                    ) {
                        val voiceId = it.arguments?.getString("id") ?: ""
                        TalkScreen(voiceId = voiceId, onBackClick = { navigator.popBackStackIfNotHome() })
                    }
                    composable(ProfileRoute.route) {
                        ProfileScreen()
                    }
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries,
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}
