package com.gdsc_cau.vridge.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel : ViewModel() {
    fun getGreeting(name: String): String {
        return "Hello $name!"
    }
}
