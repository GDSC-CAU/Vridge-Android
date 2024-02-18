package com.gdsc_cau.vridge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.gdsc_cau.vridge.ui.main.MainActivity
import com.gdsc_cau.vridge.ui.theme.VridgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoggedIn = viewModel.loginState.collectAsStateWithLifecycle().value

            VridgeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        onTryLogin = { viewModel.tryGoogleLogin(signInLauncher) }
                    )
                }

                LaunchedEffect(key1 = isLoggedIn) {
                    if (isLoggedIn) {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }
                }
            }
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
