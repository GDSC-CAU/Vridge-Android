package com.gdsc_cau.vridge.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gdsc_cau.vridge.ui.profile.navigateProfile
import com.gdsc_cau.vridge.ui.record.navigateRecord
import com.gdsc_cau.vridge.ui.talk.navigateTalk
import com.gdsc_cau.vridge.ui.voicelist.VoiceListRoute
import com.gdsc_cau.vridge.ui.voicelist.navigateVoiceList

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.VOICE_LIST.route

    val currentTab: MainTab?
        @Composable get() = currentDestination?.route?.let { MainTab.find(it) }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.id) {
                inclusive = false
            }
        }

        when (tab) {
            MainTab.VOICE_LIST -> navigateVoiceList(navOptions)
            MainTab.PROFILE -> navigateProfile(navOptions)
        }
    }

    fun navigateVoiceList(navOptions: NavOptions) {
        navController.navigateVoiceList(navOptions)
    }

    fun navigateRecord() {
        navController.navigateRecord()
    }

    fun navigateTalk(sessionId: String) {
        navController.navigateTalk(sessionId)
    }

    fun navigateProfile(navOptions: NavOptions) {
        navController.navigateProfile(navOptions)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination(VoiceListRoute.route)) {
            popBackStack()
        }
    }

    private fun isSameCurrentDestination(route: String) = navController.currentDestination?.route == route

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator =
    remember(navController) {
        MainNavigator(navController)
    }
