package com.gdsc_cau.vridge.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.ui.theme.IconBg
import com.gdsc_cau.vridge.ui.theme.OnPrimary

@Composable
fun LoginScreen(onTryLogin: () -> Unit) {
    Column(
        modifier =
            Modifier
                .background(IconBg)
                .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LoginLogo()
        LoginButton(onTryLogin = onTryLogin)
    }
}

@Composable
fun LoginLogo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(width = 200.dp, height = 200.dp),
            painter = painterResource(id = R.drawable.ic_vridge),
            contentDescription = "Login Logo"
        )
        Text(
            text = "VRIDGE",
            color = OnPrimary,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LoginButton(
    onTryLogin: () -> Unit
) {
    Image(
        modifier =
            Modifier
                .fillMaxWidth(fraction = 0.5f)
                .clickable { onTryLogin() },
        painter = painterResource(id = R.drawable.btn_signin_google),
        contentDescription = "Sign in with Google"
    )
}
