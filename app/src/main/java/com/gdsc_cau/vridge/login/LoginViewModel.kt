package com.gdsc_cau.vridge.login

import android.content.Context
import android.content.Intent
import com.gdsc_cau.vridge.MainActivity

class LoginViewModel constructor(
    private val context: Context
) {
    fun tryGoogleLogin() {
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}