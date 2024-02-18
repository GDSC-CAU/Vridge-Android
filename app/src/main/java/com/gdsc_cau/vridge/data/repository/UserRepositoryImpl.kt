package com.gdsc_cau.vridge.data.repository

import com.gdsc_cau.vridge.data.api.VridgeApi
import com.gdsc_cau.vridge.data.database.InfoDatabase
import com.gdsc_cau.vridge.data.models.User
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(
        private val api: VridgeApi,
        private val database: InfoDatabase
    ) : UserRepository {
        override suspend fun login(token: String): Boolean {
            val data =
                JsonObject(
                    mapOf(
                        "token" to JsonPrimitive(token)
                    )
                )
            val result = api.login(data)
            return result.isSuccess
        }

        override suspend fun unregister(uid: String): Boolean {
            val data =
                JsonObject(
                    mapOf(
                        "uid" to JsonPrimitive(uid)
                    )
                )

            val result = api.unregister(data)
            return result.isSuccess
        }

        override suspend fun getUser(uid: String): User {
            return database.getUser(uid)
        }
    }
