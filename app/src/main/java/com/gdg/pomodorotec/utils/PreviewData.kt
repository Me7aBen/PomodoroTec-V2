package com.gdg.pomodorotec.utils

import com.gdg.pomodorotec.data.PomodoroSession
import com.gdg.pomodorotec.data.SessionType

val fakeSessions = listOf(
    PomodoroSession(id = 1, dateMillis = System.currentTimeMillis() - 86400000, type = SessionType.WORK, durationMinutes = 25),
    PomodoroSession(id = 2, dateMillis = System.currentTimeMillis() - 80000000, type = SessionType.SHORT_BREAK, durationMinutes = 5),
    PomodoroSession(id = 3, dateMillis = System.currentTimeMillis() - 76400000, type = SessionType.WORK, durationMinutes = 25),
    PomodoroSession(id = 4, dateMillis = System.currentTimeMillis() - 10000000, type = SessionType.LONG_BREAK, durationMinutes = 15)
)
