package com.gdg.pomodorotec.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gdg.pomodorotec.data.PomodoroRepository
import com.gdg.pomodorotec.data.PomodoroSession
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(private val repository: PomodoroRepository) : ViewModel() {

    val uiState: StateFlow<HistoryUiState> =
        repository.getAllSessions()
            .map { HistoryUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HistoryUiState()
            )

    companion object {
        fun provideFactory(repository: PomodoroRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HistoryViewModel(repository) as T
                }
            }
    }
}

data class HistoryUiState(
    val sessions: List<PomodoroSession> = emptyList()
)

