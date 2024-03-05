package com.gdsc_cau.vridge.ui.voicelist

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateVoiceList(navOptions: NavOptions) {
    navigate(VoiceListRoute.route, navOptions)
}

object VoiceListRoute {
    val route = "voice_list"
}
