package com.gdg.pomodorotec.data

import com.gdg.pomodorotec.utils.fakeSessions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface PomodoroRepository {
    fun getAllSessions(): Flow<List<PomodoroSession>>
    suspend fun insertSession(session: PomodoroSession)
}

class OfflinePomodoroRepository(private val dao: PomodoroSessionDao) : PomodoroRepository {
    override fun getAllSessions(): Flow<List<PomodoroSession>> = dao.getAllSessions()

    override suspend fun insertSession(session: PomodoroSession) = dao.insertSession(session)
}

class MockPomodoroRepository : PomodoroRepository {
    override fun getAllSessions(): Flow<List<PomodoroSession>> = flowOf(fakeSessions)
    
    override suspend fun insertSession(session: PomodoroSession) {
        // No-op for mock
    }
}
