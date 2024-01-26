package com.gdsc_cau.vridge.login

import android.app.Application
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.gdsc_cau.vridge.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application
): ViewModel() {
    fun tryGoogleLogin() {
        val context = application.applicationContext
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}