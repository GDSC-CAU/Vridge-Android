package com.gdsc_cau.vridge.data.di

import com.gdsc_cau.vridge.data.repository.TalkRepository
import com.gdsc_cau.vridge.data.repository.DefaultTalkRepository
import com.gdsc_cau.vridge.data.repository.UserRepository
import com.gdsc_cau.vridge.data.repository.DefaultUserRepository
import com.gdsc_cau.vridge.data.repository.VoiceRepository
import com.gdsc_cau.vridge.data.repository.DefaultVoiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindTalkRepository(
        defaultTalkRepository: DefaultTalkRepository
    ): TalkRepository

    @Binds
    abstract fun bindUserRepository(
        defaultUserRepository: DefaultUserRepository
    ): UserRepository

    @Binds
    abstract fun bindVoiceRepository(
        defaultVoiceRepository: DefaultVoiceRepository
    ): VoiceRepository
}
