package com.gdsc_cau.vridge.data.di

import com.gdsc_cau.vridge.data.api.VridgeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideVridgeApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): VridgeApi {
        return Retrofit.Builder()
            .baseUrl(TODO("ourURL").toString())
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(VridgeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }
}
