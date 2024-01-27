package com.gdsc_cau.vridge.main

import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.profile.ProfileRoute
import com.gdsc_cau.vridge.voicelist.VoiceListRoute

enum class MainTab(
    val iconResId: Int,
    val contentDescription: String,
    val route: String,
    val title: String
) {
    VOICE_LIST(
        iconResId = R.drawable.ic_voice,
        contentDescription = "Voice List",
        route = VoiceListRoute.route,
        title = "Voice List"
    ),
    PROFILE(
        iconResId = R.drawable.ic_profile,
        contentDescription = "Profile",
        route = ProfileRoute.route,
        title = "Profile"
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
