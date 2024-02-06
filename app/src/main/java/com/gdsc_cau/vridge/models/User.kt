package com.gdsc_cau.vridge.models

data class User(
    val cntVoice: Int,
    val email: String,
    val gender: Gender,
    val name: String,
    val uid: String,
) {
    enum class Gender {
        MALE,
        FEMALE,
        NON_BINARY,
        HIDDEN,
    }
}
