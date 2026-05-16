package com.gdg.pomodorotec

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.gdg.pomodorotec.data.AppDatabase
import com.gdg.pomodorotec.data.OfflinePomodoroRepository
import com.gdg.pomodorotec.data.PomodoroRepository
import com.gdg.pomodorotec.data.settings.SettingsRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PomodoroApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

interface AppContainer {
    val pomodoroRepository: PomodoroRepository
    val settingsRepository: SettingsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    private val database: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "pomodoro_database")
            .build()
    }

    override val pomodoroRepository: PomodoroRepository by lazy {
        OfflinePomodoroRepository(database.pomodoroSessionDao())
    }

    override val settingsRepository: SettingsRepository by lazy {
        SettingsRepository(context.dataStore)
    }
}
