package com.gdg.pomodorotec.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PomodoroSessionDao {
    @Insert
    suspend fun insertSession(session: PomodoroSession)

    @Query("SELECT * FROM pomodoro_sessions ORDER BY dateMillis DESC")
    fun getAllSessions(): Flow<List<PomodoroSession>>
}
