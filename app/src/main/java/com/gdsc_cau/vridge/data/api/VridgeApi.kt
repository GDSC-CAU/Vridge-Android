package com.gdsc_cau.vridge.data.api

import com.gdsc_cau.vridge.data.models.ApiResponse
import com.gdsc_cau.vridge.data.models.VoiceListDto
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VridgeApi {
    @POST("/api/v1/user/login")
    suspend fun login(
        @Body json: JsonObject
    ): ApiResponse

    @POST("/api/v1/user/unregister")
    suspend fun unregister(
        @Body json: JsonObject
    ): ApiResponse

    @GET("api/v1/voice/list")
    suspend fun getVoiceList(
        @Body json: JsonObject
    ): VoiceListDto

    @POST("api/v1/voice/upload")
    suspend fun uploadTrainingVoice(
        @Body json: JsonObject
    ): String

    @POST("api/v1/voice/synthesize")
    suspend fun synthesizeVoice(
        @Body json: JsonObject
    ): String

    @POST("api/v1/voice/create")
    suspend fun createTts(
        @Body json: JsonObject
    ): ApiResponse
}
