package com.gdsc_cau.vridge.data.dto

import kotlinx.serialization.SerialName

data class LoginDTO(
    @SerialName("token") val token: String,
)
