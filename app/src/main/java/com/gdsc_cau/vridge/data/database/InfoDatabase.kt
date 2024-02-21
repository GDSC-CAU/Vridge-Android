package com.gdsc_cau.vridge.data.database

import com.gdsc_cau.vridge.data.models.InvalidUidException
import com.gdsc_cau.vridge.data.models.Tts
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

    suspend fun getTalks(uid: String, vid: String): List<Tts> {
        val talks = mutableListOf<Tts>()

        database.collection("user").document(uid).collection(vid)
            .get().addOnSuccessListener { documents ->
                for (document in documents) {
                    println("${document.id} => ${document.data}")
                    talks.add(
                        Tts(
                            id = document.id,
                            text = document.data["text"].toString(),
                            timestamp = document.data["timestamp"] as Long
                        )
                    )
                }
            }
        return talks
    }
}
