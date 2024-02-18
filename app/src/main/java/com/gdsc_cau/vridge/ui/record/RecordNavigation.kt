package com.gdsc_cau.vridge.ui.record

import androidx.navigation.NavController

fun NavController.navigateRecord() {
    navigate(RecordRoute.route)
}

object RecordRoute {
    val route = "record"
}
