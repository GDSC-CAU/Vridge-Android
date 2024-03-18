package com.gdsc_cau.vridge.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class TtsDTO(
    @SerialName("text") val text: String,
    @SerialName("uid") val uid: String,
    @SerialName("vid") val vid: String,
    @SerialName("tid") val tid: String,
    @SerialName("pitch") val pitch: Int,
)
