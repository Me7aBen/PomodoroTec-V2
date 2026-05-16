package com.gdg.pomodorotec.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PomodoroSession::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pomodoroSessionDao(): PomodoroSessionDao
}
