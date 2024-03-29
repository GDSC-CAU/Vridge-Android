package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.InfoDatabase
import com.gdsc_cau.vridge.data.dto.LoginDTO
import com.gdsc_cau.vridge.data.dto.UidDTO
import com.gdsc_cau.vridge.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject

class DefaultUserRepository
    @Inject
    constructor(
        private val api: VridgeApi,
        private val database: InfoDatabase,
        private val auth: FirebaseAuth
    ) : UserRepository {
        override suspend fun login(token: String): Boolean {
            val data = LoginDTO(token)
            val result = api.login(data)
            return result.success
        }

        override suspend fun unregister(): Boolean {
            val uid = getUid()
            val data = UidDTO(uid)

            val result = api.unregister(data)
            return result.success
        }

        override fun getCurrentUser(): FirebaseUser? {
            return auth.currentUser
        }

        override fun signOut() {
            auth.signOut()
        }

        override fun getUid(): String {
            return auth.currentUser?.uid ?: ""
        }

        override suspend fun getUserInfo(): User {
            val uid = getUid()
            return database.getUser(uid)
        }
    }
