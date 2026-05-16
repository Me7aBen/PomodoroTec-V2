package com.gdg.pomodorotec.ui.timer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun TimerScreen(
    viewModel: TimerViewModel,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(300.dp)
        ) {
            // Background Circle
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    style = Stroke(width = 12.dp.toPx())
                )
            }

            // Progress Circle
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = Color.Red,
                    startAngle = -90f,
                    sweepAngle = 360f * uiState.progress,
                    useCenter = false,
                    style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                )
            }

            // Countdown Text
            Text(
                text = uiState.formattedTime,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!uiState.isRunning) {
                Button(
                    onClick = { viewModel.startTimer() },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Iniciar")
                }
            } else {
                Button(
                    onClick = { viewModel.pauseTimer() },
                    modifier = Modifier.width(120.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Pausar")
                }
            }

            OutlinedButton(
                onClick = { viewModel.resetTimer() },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Reiniciar")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            TextButton(onClick = onNavigateToHistory) {
                Text("Ver Historial")
            }
            TextButton(onClick = onNavigateToSettings) {
                Text("Configuración")
            }
        }
    }
}
