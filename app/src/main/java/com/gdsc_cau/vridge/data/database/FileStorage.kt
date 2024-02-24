package com.gdsc_cau.vridge.data.database

import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FileStorage @Inject constructor(private val storageReference: StorageReference) {
    suspend fun uploadFile(uid: String, vid: String, fileName: String, data: ByteArray) = callbackFlow {
        val fileReference = storageReference.child(uid).child(vid).child("train").child(fileName)
        fileReference.putBytes(data).addOnCompleteListener {
            trySend(it.isSuccessful)
        }
        awaitClose()
    }.first()

    fun downloadFile(path: String) {
        val fileReference = storageReference.child(path)
        fileReference.getBytes(1024 * 1024)
    }

    fun getDownloadUrl(path: String): String {
        val fileReference = storageReference.child(path)
        return fileReference.downloadUrl.result.toString()
    }

    fun existTts(uid: String, vid: String, ttsId: String): Boolean {
        return storageReference.child(uid).child(vid).listAll()
            .result.items.any { it.name == "$ttsId.wav" }
    }
}
