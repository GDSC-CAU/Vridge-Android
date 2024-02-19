package com.gdsc_cau.vridge.ui.login

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.gdsc_cau.vridge.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val repository: UserRepository
    ) :
    ViewModel() {
        private val _loginState = MutableStateFlow(false)
        val loginState: StateFlow<Boolean>
            get() = _loginState

        fun tryGoogleLogin(signInLauncher: ActivityResultLauncher<Intent>) {
            val signInProvider = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
            val signInIntent =
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(signInProvider)
                    .build()
            signInLauncher.launch(signInIntent)
        }

        fun deleteUser() {
            repository.getCurrentUser()?.delete()
        }

        fun signOut() {
            repository.signOut()
        }

        fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
            if (result.resultCode == ComponentActivity.RESULT_OK && repository.getCurrentUser() != null) {
                result.idpResponse?.idpToken?.let {
                    viewModelScope.launch {
                        _loginState.emit(true)
//                        TODO("Backend response check")
//                        if (repository.login(it)) {
//                            _loginState.emit(true)
//                        }
                    }
                }
            } else {
                // TODO: Show Snack Bar?
            }
        }
    }
