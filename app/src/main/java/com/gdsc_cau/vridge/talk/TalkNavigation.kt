package com.gdsc_cau.vridge.talk

import androidx.navigation.NavController

fun NavController.navigateTalk(sessionId: String) {
    navigate(TalkRoute.detailRoute(sessionId))
}

object TalkRoute {
    val route = "talk"

    fun detailRoute(sessionId: String): String = "$route/$sessionId"
}
