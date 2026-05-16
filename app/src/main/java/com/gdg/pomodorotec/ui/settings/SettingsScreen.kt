package com.gdg.pomodorotec.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Configuración",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Work Duration Slider
        Text(
            text = "Tiempo de Trabajo: ${uiState.workDurationMinutes} min",
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = uiState.workDurationMinutes.toFloat(),
            onValueChange = { viewModel.updateWorkDuration(it.toInt()) },
            valueRange = 1f..60f,
            steps = 59,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Short Break Duration Slider
        Text(
            text = "Tiempo de Descanso: ${uiState.shortBreakDurationMinutes} min",
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = uiState.shortBreakDurationMinutes.toFloat(),
            onValueChange = { viewModel.updateShortBreakDuration(it.toInt()) },
            valueRange = 1f..30f,
            steps = 29,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
