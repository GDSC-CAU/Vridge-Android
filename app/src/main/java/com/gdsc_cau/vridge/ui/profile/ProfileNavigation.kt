package com.gdsc_cau.vridge.ui.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateProfile(navOptions: NavOptions) {
    navigate(ProfileRoute.route, navOptions)
}

object ProfileRoute {
    val route = "profile"
}
