package com.gdsc_cau.vridge.data.database

import com.gdsc_cau.vridge.data.models.InvalidUidException
import com.gdsc_cau.vridge.data.models.Tts
import com.gdsc_cau.vridge.data.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
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

    fun getTalks(uid: String, vid: String): List<Tts> {
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

    fun getVoiceList(uid: String): Map<String, String> {
        var voiceList: Map<String, String> = mapOf()

        database.collection("user").document(uid)
            .get().addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data
                    if (data != null) {
                        voiceList = data.get("voice") as? Map<String, String> ?: mapOf()
                    }
                }
            }
        return voiceList
    }

    fun saveTts(uid: String, vid: String, tts: Tts) {
        database.collection("user").document(uid).collection(vid).document(tts.id).set(
            mapOf(
                "text" to tts.text,
                "timestamp" to tts.timestamp
            )
        )
    }

    suspend fun saveVoice(uid: String, vid: String, name: String, pitch: Float) = callbackFlow {
        val userDoc = database.collection("user").document(uid).get().await()
        val count = userDoc.data?.get("cntVoice") as Long
        database.collection("user").document(uid).set(mapOf("cntVoice" to count + 1))
            .addOnSuccessListener {
                database.collection("user").document(uid)
                    .collection(vid).document("attr").set(
                        mapOf(
                            "name" to name,
                            "pitch" to pitch
                        ),
                        SetOptions.merge()
                    ).addOnCompleteListener {
                        trySend(it.isSuccessful)
                    }
            }.addOnFailureListener {
                trySend(false)
            }
        awaitClose()
    }.first()
}
