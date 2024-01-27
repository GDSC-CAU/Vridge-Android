package com.gdsc_cau.vridge.voicelist

import androidx.navigation.NavController

fun NavController.navigateVoiceList() {
    navigate(VoiceListRoute.route)
}

object VoiceListRoute {
    val route = "voice_list"
}
