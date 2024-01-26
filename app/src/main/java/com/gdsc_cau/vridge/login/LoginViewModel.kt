package com.gdsc_cau.vridge.login

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.gdsc_cau.vridge.utils.FirebaseAuthUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val isLoggedIn = MutableLiveData(false)

    fun tryGoogleLogin(signInLauncher: ActivityResultLauncher<Intent>) {
        FirebaseAuthUtil.tryGoogleLogin(signInLauncher)
    }

    fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == ComponentActivity.RESULT_OK
            && FirebaseAuthUtil.getCurrentUser() != null) {
            isLoggedIn.postValue(true)
        } else {
            // TODO: Show Snack Bar?
        }
    }
}