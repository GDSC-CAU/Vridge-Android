package com.gdsc_cau.vridge.models

data class Voice(
    val data: String,
    val gender: Gender,
    val id: String,
    val name: String,
    val recorder: String,
)
