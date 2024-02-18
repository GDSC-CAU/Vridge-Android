package com.gdsc_cau.vridge.data.di

import com.gdsc_cau.vridge.data.repository.TalkRepository
import com.gdsc_cau.vridge.data.repository.TalkRepositoryImpl
import com.gdsc_cau.vridge.data.repository.UserRepository
import com.gdsc_cau.vridge.data.repository.UserRepositoryImpl
import com.gdsc_cau.vridge.data.repository.VoiceRepository
import com.gdsc_cau.vridge.data.repository.VoiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindTalkRepository(
        talkRepositoryImpl: TalkRepositoryImpl
    ): TalkRepository

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindVoiceRepository(
        voiceRepositoryImpl: VoiceRepositoryImpl
    ): VoiceRepository
}
