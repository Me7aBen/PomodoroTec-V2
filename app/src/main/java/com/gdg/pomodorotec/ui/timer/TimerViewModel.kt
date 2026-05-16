package com.gdg.pomodorotec.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gdg.pomodorotec.data.PomodoroRepository
import com.gdg.pomodorotec.data.PomodoroSession
import com.gdg.pomodorotec.data.SessionType
import com.gdg.pomodorotec.data.settings.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewModel(
    private val repository: PomodoroRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private var currentDurationMinutes = 25
    private var initialTime = currentDurationMinutes * 60L

    private val _uiState = MutableStateFlow(TimerUiState(timeLeft = initialTime, totalTime = initialTime))
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            settingsRepository.userSettingsFlow.collect { settings ->
                if (!_uiState.value.isRunning) {
                    currentDurationMinutes = settings.workDurationMinutes
                    initialTime = currentDurationMinutes * 60L
                    _uiState.update { it.copy(timeLeft = initialTime, totalTime = initialTime) }
                }
            }
        }
    }

    fun startTimer() {
        if (_uiState.value.isRunning) return

        _uiState.update { it.copy(isRunning = true) }
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeft > 0) {
                delay(1000)
                _uiState.update { it.copy(timeLeft = it.timeLeft - 1) }
            }
            _uiState.update { it.copy(isRunning = false) }

            // Guardar la sesión en la base de datos cuando termina
            repository.insertSession(
                PomodoroSession(
                    dateMillis = System.currentTimeMillis(),
                    type = SessionType.WORK,
                    durationMinutes = currentDurationMinutes
                )
            )
            resetTimer() // Reiniciamos visualmente
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.update { it.copy(isRunning = false) }
    }

    fun resetTimer() {
        pauseTimer()
        _uiState.update { it.copy(timeLeft = initialTime, totalTime = initialTime, isRunning = false) }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    companion object {
        fun provideFactory(
            repository: PomodoroRepository,
            settingsRepository: SettingsRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TimerViewModel(repository, settingsRepository) as T
                }
            }
    }
}

data class TimerUiState(
    val timeLeft: Long = 0,
    val isRunning: Boolean = false,
    val totalTime: Long = 25 * 60L
) {
    val progress: Float
        get() = if (totalTime > 0) timeLeft.toFloat() / totalTime else 0f

    val formattedTime: String
        get() {
            val minutes = timeLeft / 60
            val seconds = timeLeft % 60
            return "%02d:%02d".format(minutes, seconds)
        }
}
