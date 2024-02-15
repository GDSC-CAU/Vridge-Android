package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.models.User

interface UserRepository {
    suspend fun login(token: String): Boolean

    suspend fun getUser(uid: String): User
}
