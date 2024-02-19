package com.gdsc_cau.vridge.data.models

data class User(
    val cntVoice: Int = -1,
    val email: String = "",
    val gender: Gender = Gender.HIDDEN,
    val name: String = "",
    val uid: String = ""
)
