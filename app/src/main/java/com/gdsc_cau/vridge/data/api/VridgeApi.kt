package com.gdsc_cau.vridge.data.api

import com.gdsc_cau.vridge.data.models.ApiResponse
import com.gdsc_cau.vridge.data.models.TtsDTO
import com.gdsc_cau.vridge.data.models.VoiceListDTO
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        @Query("uid") uid: String
    ): VoiceListDTO

    @POST("api/v1/voice/upload")
    suspend fun uploadTrainingVoice(
        @Body json: JsonObject
    ): Boolean

    @POST("api/v1/voice/synthesize")
    suspend fun synthesizeVoice(
        @Body json: JsonObject
    ): String

    @POST("api/v1/voice/create")
    suspend fun createTts(
        @Body data: TtsDTO
    ): ApiResponse
}
