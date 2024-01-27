package com.gdsc_cau.vridge.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdsc_cau.vridge.profile.navigateProfile
import com.gdsc_cau.vridge.record.navigateRecord
import com.gdsc_cau.vridge.talk.navigateTalk
import com.gdsc_cau.vridge.voicelist.VoiceListRoute
import com.gdsc_cau.vridge.voicelist.navigateVoiceList

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.VOICE_LIST.route

    val currentTab: MainTab?
        @Composable get() = currentDestination?.route?.let { MainTab.find(it) }

    fun navigate(tab: MainTab) {
        when (tab) {
            MainTab.VOICE_LIST -> navigateVoiceList()
            MainTab.PROFILE -> navigateProfile()
        }
    }

    fun navigateVoiceList() {
        navController.navigateVoiceList()
    }

    fun navigateRecord() {
        navController.navigateRecord()
    }

    fun navigateTalk(sessionId: String) {
        navController.navigateTalk(sessionId)
    }

    fun navigateProfile() {
        navController.navigateProfile()
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
