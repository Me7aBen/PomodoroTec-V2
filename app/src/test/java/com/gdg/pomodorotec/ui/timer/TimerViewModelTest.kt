package com.gdg.pomodorotec.ui.timer

import com.gdg.pomodorotec.data.MockPomodoroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TimerViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: TimerViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TimerViewModel(MockPomodoroRepository())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun timer_initialState_isCorrect() {
        val state = viewModel.uiState.value
        assertEquals(2 * 60L, state.timeLeft)
        assertFalse(state.isRunning)
    }

    @Test
    fun timer_start_updatesIsRunning() {
        viewModel.startTimer()
        assertTrue(viewModel.uiState.value.isRunning)
    }

    @Test
    fun timer_pause_updatesIsRunning() {
        viewModel.startTimer()
        viewModel.pauseTimer()
        assertFalse(viewModel.uiState.value.isRunning)
    }

    @Test
    fun timer_reset_restoresInitialTime() {
        viewModel.startTimer()
        // Advance time a bit (virtually)
        testDispatcher.scheduler.advanceTimeBy(2000)
        
        viewModel.resetTimer()
        val state = viewModel.uiState.value
        assertEquals(2 * 60L, state.timeLeft)
        assertFalse(state.isRunning)
    }
}
