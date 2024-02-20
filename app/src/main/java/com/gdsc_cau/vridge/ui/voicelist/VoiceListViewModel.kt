package com.gdsc_cau.vridge.ui.voicelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc_cau.vridge.data.models.Voice
import com.gdsc_cau.vridge.data.repository.VoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoiceListViewModel @Inject constructor(private val repository: VoiceRepository) : ViewModel() {
    private val _voiceList = MutableStateFlow<List<Voice>>(emptyList())
    val voiceList: StateFlow<List<Voice>> = _voiceList

    init {
        getVoices()
    }
    fun getVoices() {
        viewModelScope.launch {
            _voiceList.emit(repository.getVoiceList())
        }
    }

    fun synthesize(voiceList: List<String>) {
        viewModelScope.launch {
            repository.synthesize(voiceList)
        }
    }

}
