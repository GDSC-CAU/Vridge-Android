package com.gdsc_cau.vridge.data.models

data class User(
    val cntVoice: Int,
    val email: String,
    val gender: Gender,
    val name: String,
    val uid: String
)
