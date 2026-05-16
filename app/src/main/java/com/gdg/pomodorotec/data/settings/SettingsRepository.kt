package com.gdg.pomodorotec.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val WORK_DURATION = intPreferencesKey("work_duration")
        val SHORT_BREAK_DURATION = intPreferencesKey("short_break_duration")
        val LONG_BREAK_DURATION = intPreferencesKey("long_break_duration")
    }

    val userSettingsFlow: Flow<UserSettings> = dataStore.data
        .map { preferences ->
            UserSettings(
                workDurationMinutes = preferences[WORK_DURATION] ?: 25,
                shortBreakDurationMinutes = preferences[SHORT_BREAK_DURATION] ?: 5,
                longBreakDurationMinutes = preferences[LONG_BREAK_DURATION] ?: 15
            )
        }

    suspend fun saveWorkDuration(duration: Int) {
        dataStore.edit { preferences ->
            preferences[WORK_DURATION] = duration
        }
    }

    suspend fun saveShortBreakDuration(duration: Int) {
        dataStore.edit { preferences ->
            preferences[SHORT_BREAK_DURATION] = duration
        }
    }

    suspend fun saveLongBreakDuration(duration: Int) {
        dataStore.edit { preferences ->
            preferences[LONG_BREAK_DURATION] = duration
        }
    }
}

data class UserSettings(
    val workDurationMinutes: Int = 25,
    val shortBreakDurationMinutes: Int = 5,
    val longBreakDurationMinutes: Int = 15
)
