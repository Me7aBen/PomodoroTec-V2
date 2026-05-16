package com.gdg.pomodorotec.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pomodoro_sessions")
data class PomodoroSession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateMillis: Long,
    val type: SessionType,
    val durationMinutes: Int
)

enum class SessionType {
    WORK, SHORT_BREAK, LONG_BREAK
}
