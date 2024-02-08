package com.gdsc_cau.vridge.ui.profile

import androidx.navigation.NavController

fun NavController.navigateProfile() {
    navigate(ProfileRoute.route)
}

object ProfileRoute {
    val route = "profile"
}
