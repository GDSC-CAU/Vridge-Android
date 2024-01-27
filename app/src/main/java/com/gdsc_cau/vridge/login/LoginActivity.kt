package com.gdsc_cau.vridge.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.gdsc_cau.vridge.MainActivity
import com.gdsc_cau.vridge.ui.theme.VridgeTheme
import com.gdsc_cau.vridge.utils.FirebaseAuthUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VridgeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginView(
                        onTryLogin = { viewModel.tryGoogleLogin(signInLauncher) }
                    )
                }
            }
        }

        viewModel.isLoggedIn.observe(this) {
            if (it.equals(true)) {
                Log.d("USER Logged In", FirebaseAuthUtil.getCurrentUser()!!.email!!)
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }

        if (FirebaseAuthUtil.getCurrentUser() != null) {
            viewModel.isLoggedIn.postValue(true)
        }
    }

    private val signInLauncher =
        registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) {
                res ->
            viewModel.onSignInResult(res)
        }
}
