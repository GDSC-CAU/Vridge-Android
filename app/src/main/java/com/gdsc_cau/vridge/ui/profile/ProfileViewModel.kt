package com.gdsc_cau.vridge.ui.profile

import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc_cau.vridge.data.models.User
import com.gdsc_cau.vridge.data.repository.UserRepository
import com.gdsc_cau.vridge.ui.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: StateFlow<Boolean> = _isLoggedOut

    init {
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            _user.emit(repository.getUserInfo(repository.getUid()))
        }
    }

    fun signOut() {
        repository.signOut()
        _isLoggedOut.value = true
    }

    fun unregister() {
        viewModelScope.launch {
            val result = repository.unregister(repository.getUid())
            if (result) {
                _isLoggedOut.value = true
            }
        }
    }
}
