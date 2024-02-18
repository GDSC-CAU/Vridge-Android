package com.gdsc_cau.vridge.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor() : ViewModel() {
        fun getGreeting(name: String): String {
            return "Hello $name!"
        }
}
