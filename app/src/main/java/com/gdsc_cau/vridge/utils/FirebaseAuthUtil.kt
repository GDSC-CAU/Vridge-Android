package com.gdsc_cau.vridge.utils

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

object FirebaseAuthUtil {
    private lateinit var auth: FirebaseAuth

    private fun initFirebaseAuth() {
        auth = Firebase.auth
    }

    fun getCurrentUser(): FirebaseUser? {
        initFirebaseAuth()
        return auth.currentUser
    }

    fun tryGoogleLogin(signInLauncher: ActivityResultLauncher<Intent>) {
        initFirebaseAuth()
        val signInProvider = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val signInIntent =
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(signInProvider)
                .build()
        signInLauncher.launch(signInIntent)
    }

    fun signOut() {
        initFirebaseAuth()
        auth.signOut()
    }
}
