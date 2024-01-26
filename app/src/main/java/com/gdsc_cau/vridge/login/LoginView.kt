package com.gdsc_cau.vridge.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gdsc_cau.vridge.MainActivity
import com.gdsc_cau.vridge.R

@Composable
fun LoginView() {
    val modifier = Modifier

    Column(
        modifier = modifier
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LoginLogo(modifier = modifier)
        LoginButton(modifier = modifier)
    }
}

@Composable
fun LoginLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(width = 200.dp, height = 200.dp),
        painter = painterResource(id = android.R.mipmap.sym_def_app_icon),
        contentDescription = "Login Logo"
    )
}

@Composable
fun LoginButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Image(
        modifier = modifier
            .fillMaxWidth(fraction = 0.5f)
            .clickable {
                context.startActivity(Intent(context, MainActivity::class.java))
            },
        painter = painterResource(id = R.drawable.btn_signin_google),
        contentDescription = "Sign in with Google"
    )
}