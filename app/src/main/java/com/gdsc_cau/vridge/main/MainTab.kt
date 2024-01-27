package com.gdsc_cau.vridge.main

import com.gdsc_cau.vridge.R

enum class MainTab(
    val iconResId: Int,
    val contentDescription: String,
    val route: String
) {
    VOICE_LIST(
        iconResId = R.drawable.ic_voice,
        contentDescription = "Voice List",
        route = VoiceListRoute.route
    ),
    PROFILE(
        iconResId = R.drawable.ic_profile,
        contentDescription = "Profile",
        route = ProfileRoute.route
    );

    companion object {
        operator fun contains(route: String): Boolean {
            return entries.map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return entries.find { it.route == route }
        }
    }
}
