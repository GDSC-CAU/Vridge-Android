package com.gdsc_cau.vridge.data.database

import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FileStorage @Inject constructor(private val storageReference: StorageReference) {
    suspend fun uploadFile(uid: String, vid: String, fileName: String, data: ByteArray) {
        val fileReference = storageReference.child(uid).child(vid).child("train").child(fileName)
        fileReference.putBytes(data).await()
    }

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
