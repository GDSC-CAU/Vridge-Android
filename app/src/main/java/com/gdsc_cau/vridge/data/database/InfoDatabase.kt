package com.gdsc_cau.vridge.data.database

import com.gdsc_cau.vridge.data.models.InvalidUidException
import com.gdsc_cau.vridge.data.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InfoDatabase
    @Inject
    constructor(
        private val database: FirebaseFirestore
    ) {
        suspend fun getUser(uid: String): User {
            return database.collection("user")
                .document(uid)
                .get().await()
                .toObject(User::class.java)
                ?: throw InvalidUidException()
        }
    }
