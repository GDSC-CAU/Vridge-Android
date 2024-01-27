package com.gdsc_cau.vridge.login

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.gdsc_cau.vridge.utils.FirebaseAuthUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor() :
    ViewModel() {
        private val _loginState = MutableStateFlow(false)
        val loginState: StateFlow<Boolean>
            get() = _loginState

        fun tryGoogleLogin(signInLauncher: ActivityResultLauncher<Intent>) {
            FirebaseAuthUtil.tryGoogleLogin(signInLauncher)
        }

        fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
            if (result.resultCode == ComponentActivity.RESULT_OK &&
                FirebaseAuthUtil.getCurrentUser() != null
            ) {
                emitLoginState(true)
            } else {
                // TODO: Show Snack Bar?
            }
        }

        fun emitLoginState(state: Boolean) {
            viewModelScope.launch {
                _loginState.emit(state)
            }
        }
    }
