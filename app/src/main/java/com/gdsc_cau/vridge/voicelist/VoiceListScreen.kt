package com.gdsc_cau.vridge.voicelist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdsc_cau.vridge.main.Greeting
import com.gdsc_cau.vridge.main.MainNavigator
import com.gdsc_cau.vridge.models.Voice

@Composable
fun VoiceListScreen(
    padding: PaddingValues,
    onVoiceClick: (Voice) -> Unit,
    viewModel: VoiceListViewModel = hiltViewModel()
) {
    val voices = viewModel.voices

    if (voices.isEmpty()) {
        EmptyVoiceList()
    } else {
        GridVoiceList(voices, onVoiceClick)
    }
}

@Composable
fun EmptyVoiceList() {
    Greeting(name = "No voices found")
}

@Composable
fun GridVoiceList(voices: List<Voice>, onVoiceClick: (Voice) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {

    }
}

@Composable
fun VoiceListItem(
    voice: Voice,
    onVoiceClick: (Voice) -> Unit
) {
    Button(onClick = { onVoiceClick(voice) }) {
        Greeting(name = voice.name)
    }
}
