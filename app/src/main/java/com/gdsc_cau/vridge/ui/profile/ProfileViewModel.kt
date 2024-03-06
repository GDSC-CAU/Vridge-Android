package com.gdsc_cau.vridge.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc_cau.vridge.data.models.User
import com.gdsc_cau.vridge.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: StateFlow<Boolean> = _isLoggedOut

    init {
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                _user.emit(repository.getUserInfo(repository.getUid()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
