package com.gdg.pomodorotec.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gdg.pomodorotec.data.settings.SettingsRepository
import com.gdg.pomodorotec.data.settings.UserSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {

    val uiState: StateFlow<UserSettings> = repository.userSettingsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserSettings()
        )

    fun updateWorkDuration(duration: Int) {
        viewModelScope.launch {
            repository.saveWorkDuration(duration)
        }
    }

    fun updateShortBreakDuration(duration: Int) {
        viewModelScope.launch {
            repository.saveShortBreakDuration(duration)
        }
    }

    companion object {
        fun provideFactory(repository: SettingsRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(repository) as T
                }
            }
    }
}
