package com.gdsc_cau.vridge.ui.record

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc_cau.vridge.data.repository.VoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

val LOG_TAG = "RecordViewModel"

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val repository: VoiceRepository
) : ViewModel() {
    private val _recordIndex = MutableStateFlow(1)
    val recordIndex: StateFlow<Int> = _recordIndex

    private val _recordText = MutableStateFlow(repository.getTrainingText(recordIndex.value))
    val recordText: StateFlow<String> = _recordText

    private val _isRecorded = MutableStateFlow(false)
    val isRecorded: StateFlow<Boolean> = _isRecorded

    private val _finished = MutableStateFlow(false)
    val finished: StateFlow<Boolean> = _finished

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    private lateinit var fileDir: String
    private lateinit var fileName: String

    val scriptSize = repository.getScriptSize()

    fun setFileName(name: String) {
        viewModelScope.launch {
            fileDir = name
            fileName = "$fileDir/${recordIndex.value}.m4a"
            repository.beforeRecord(fileDir)
            Log.d(LOG_TAG, "fileDir: $fileDir, fileName: $fileName")
        }
    }

    fun getNextText() {
        viewModelScope.launch {
            _isLoading.emit(true)
            if (recordIndex.value == repository.getScriptSize()) {
                if (!repository.saveVoice(recordIndex.value)) {
                    _finished.emit(false)
                } else {
                    _finished.emit(repository.afterRecord())
                }
            } else {
                val result = repository.saveVoice(recordIndex.value)
                if (result) {
                    _recordIndex.emit(recordIndex.value + 1)
                    fileName = "$fileDir/${recordIndex.value}.m4a"
                    _recordText.emit(repository.getTrainingText(recordIndex.value))
                    _isRecorded.emit(false)
                }
            }
            _isLoading.emit(false)
        }
    }


    fun onRecord(start: Boolean, recorder: MediaRecorder? = null) {
        viewModelScope.launch {
            if (start) {
                this@RecordViewModel.recorder = recorder
                startRecording()
            } else {
                stopRecording()
                _isRecorded.emit(true)
            }
        }
    }

    fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startRecording() {
        recorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
    }
}
