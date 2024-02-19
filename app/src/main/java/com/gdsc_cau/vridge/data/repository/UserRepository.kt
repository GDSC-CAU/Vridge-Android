package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.models.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun login(token: String): Boolean

    suspend fun unregister(uid: String): Boolean

    fun getCurrentUser(): FirebaseUser?

    fun signOut()

    fun getUid(): String

    suspend fun getUserInfo(uid: String): User
}
