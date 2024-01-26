package com.gdsc_cau.vridge.Login

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gdsc_cau.vridge.MainActivity

@Composable
fun LoginView() {
    Column {
        LoginButton()
    }
}

@Composable
fun LoginButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    
    ElevatedButton(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) {
        Text("Go to Main")
    }
}