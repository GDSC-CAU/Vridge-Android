package com.gdsc_cau.vridge.main

import android.util.Log
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
import com.gdsc_cau.vridge.profile.ProfileRoute
import com.gdsc_cau.vridge.profile.ProfileScreen
import com.gdsc_cau.vridge.record.RecordRoute
import com.gdsc_cau.vridge.record.RecordScreen
import com.gdsc_cau.vridge.talk.TalkRoute
import com.gdsc_cau.vridge.talk.TalkScreen
import com.gdsc_cau.vridge.voicelist.VoiceListRoute
import com.gdsc_cau.vridge.voicelist.VoiceListScreen

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
                        .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination
                ) {
                    composable(VoiceListRoute.route) {
                        VoiceListScreen(
                            padding = padding,
                            onVoiceClick = { navigator.navigateTalk(it) }
                        )
                    }
                    composable(RecordRoute.route) {
                        RecordScreen()
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
                        val sessionId = it.arguments?.getString("id") ?: ""
                        Log.d("MainScreen", "sessionId: $sessionId")
                        TalkScreen(sessionId)
                    }
                    composable(ProfileRoute.route) {
                        ProfileScreen()
                    }
                }
            }
        },
        topBar = {
            MainTopBar(
                title = navigator.currentTab?.title ?: "",
            )
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
