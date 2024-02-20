package com.gdsc_cau.vridge.ui.voicelist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.data.models.Gender
import com.gdsc_cau.vridge.data.models.Voice
import com.gdsc_cau.vridge.ui.theme.OnPrimaryLight
import com.gdsc_cau.vridge.ui.theme.Primary
import com.gdsc_cau.vridge.ui.theme.White

val dummyVoices = mutableListOf<Voice>().apply {
    add(Voice(id = "1", name = "Voice 1"))
    add(Voice(id = "2", name = "Voice 2"))
    add(Voice(id = "3", name = "Voice 3"))
}

@Composable
fun VoiceListScreen(
    padding: PaddingValues,
    onVoiceClick: (Voice) -> Unit,
    onRecordClick: () -> Unit,
    viewModel: VoiceListViewModel = hiltViewModel()
) {
    val voices = viewModel.voiceList.collectAsStateWithLifecycle().value

    if (voices.isEmpty()) {
        EmptyVoiceList(onRecordClick)
    } else {
        GridVoiceList(voices, onRecordClick, { viewModel.synthesize(it) }, onVoiceClick)
    }
}

@Composable
fun EmptyVoiceList(onRecordClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(id = R.string.empty_voice_list))
        ElevatedButton(
            onClick = onRecordClick,
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Primary, contentColor = White),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 2.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(stringResource(R.string.btn_add_voice), modifier = Modifier.padding(horizontal = 4.dp))
        }
    }
}

@Composable
fun GridVoiceList(
    voices: List<Voice>,
    onRecordClick: () -> Unit,
    onSynthClick: (List<String>) -> Unit,
    onVoiceClick: (Voice) -> Unit
) {
    val selectedIds = rememberSaveable { mutableStateOf(emptySet<String>()) }
    val inSelectionMode = rememberSaveable { mutableStateOf(false) }

    BackHandler {
        if (inSelectionMode.value) {
            inSelectionMode.value = false
            selectedIds.value = emptySet()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(voices.size, key = { it }) { index ->
                val selected = selectedIds.value.contains(voices[index].id)
                VoiceListItem(
                    voices[index],
                    inSelectionMode.value,
                    selected,
                    Modifier.clickable {
                        if (inSelectionMode.value) {
                            selectedIds.value =
                                if (selected) {
                                    selectedIds.value.minus(voices[index].id)
                                } else if (selectedIds.value.size < 2) {
                                    selectedIds.value.plus(voices[index].id)
                                } else {
                                    selectedIds.value
                                }
                        } else {
                            onVoiceClick(voices[index])
                        }
                    }
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            if (inSelectionMode.value) {
                Button(
                    onClick = { onSynthClick(selectedIds.value.toList()) },
                    elevation = ButtonDefaults.buttonElevation(4.dp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        "합성 ( ${selectedIds.value.size} / 2 )",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            } else {
                Button(
                    onClick = onRecordClick,
                    elevation = ButtonDefaults.buttonElevation(4.dp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        "추가",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
                Button(
                    onClick = { inSelectionMode.value = true },
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        "합성",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun VoiceListItem(
    voice: Voice,
    inSelectionMode: Boolean,
    selected: Boolean,
    modifier: Modifier
) {
    Card(
        modifier =
        modifier
            .padding(8.dp)
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp, pressedElevation = 2.dp)
    ) {
        Surface {
            if (inSelectionMode && selected) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = null,
                    tint = OnPrimaryLight,
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxSize()
                )
            }

            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = voice.name, textAlign = TextAlign.Center)
            }
        }
    }
}
