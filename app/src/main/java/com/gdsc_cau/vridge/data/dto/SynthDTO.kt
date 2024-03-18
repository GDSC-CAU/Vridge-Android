package com.gdsc_cau.vridge.data.dto

import kotlinx.serialization.SerialName

data class SynthDTO(
    @SerialName("uid") val uid: String,
    @SerialName("vid") val vid: List<String>,
)
