package com.gdsc_cau.vridge.ui.main

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.gdsc_cau.vridge.ui.theme.VridgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO
    ).apply {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            plus(Manifest.permission.READ_MEDIA_AUDIO)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result: Map<String, Boolean> ->
        Log.d("Permission", result.toString())
        val deniedList: List<String> = result.filter {
            !it.value
        }.map {
            it.key
        }

        if (deniedList.isNotEmpty()) {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher.launch(permissions)

        setContent {
            VridgeTheme {
                MainScreen()
            }
        }
    }
}
