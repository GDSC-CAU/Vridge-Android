package com.gdsc_cau.vridge.data.api

import com.gdsc_cau.vridge.data.dto.LoginDTO
import com.gdsc_cau.vridge.data.dto.SynthDTO
import com.gdsc_cau.vridge.data.models.ApiResponse
import com.gdsc_cau.vridge.data.dto.TtsDTO
import com.gdsc_cau.vridge.data.dto.UidDTO
import com.gdsc_cau.vridge.data.dto.VoiceDTO
import com.gdsc_cau.vridge.data.models.VoiceListResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VridgeApi {
    @POST("/api/v1/user/login")
    suspend fun login(
        @Body data: LoginDTO
    ): ApiResponse

    @POST("/api/v1/user/unregister")
    suspend fun unregister(
        @Body data: UidDTO
    ): ApiResponse

    @GET("api/v1/voice/list")
    suspend fun getVoiceList(
        @Query("uid") uid: String
    ): VoiceListResponse

    @POST("api/v1/voice/upload")
    suspend fun uploadTrainingVoice(
        @Body data: VoiceDTO
    ): Boolean

    @POST("api/v1/voice/synthesize")
    suspend fun synthesizeVoice(
        @Body data: SynthDTO
    ): String

    @POST("api/v1/voice/create")
    suspend fun createTts(
        @Body data: TtsDTO
    ): ApiResponse
}
