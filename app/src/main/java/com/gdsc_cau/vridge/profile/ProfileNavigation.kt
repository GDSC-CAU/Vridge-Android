package com.gdsc_cau.vridge.profile

import androidx.navigation.NavController

fun NavController.navigateProfile() {
    navigate(ProfileRoute.route)
}

object ProfileRoute {
    val route = "profile"
}
