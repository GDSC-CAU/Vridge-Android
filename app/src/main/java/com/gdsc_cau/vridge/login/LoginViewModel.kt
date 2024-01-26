package com.gdsc_cau.vridge.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val isLoggedIn = MutableLiveData(false)

    fun tryGoogleLogin() {
        // TODO: Google Sign-In Transaction
        isLoggedIn.postValue(true)
    }
}