package com.gdsc_cau.vridge.data.dto

import kotlinx.serialization.SerialName

data class VoiceDTO(
    @SerialName("uid") val uid: String,
    @SerialName("vid") val vid: String,
)
