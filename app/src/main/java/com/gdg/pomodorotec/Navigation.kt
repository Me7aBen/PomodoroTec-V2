package com.gdg.pomodorotec

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.gdg.pomodorotec.ui.history.HistoryScreen
import com.gdg.pomodorotec.ui.history.HistoryViewModel
import com.gdg.pomodorotec.ui.settings.SettingsScreen
import com.gdg.pomodorotec.ui.settings.SettingsViewModel
import com.gdg.pomodorotec.ui.timer.TimerScreen
import com.gdg.pomodorotec.ui.timer.TimerViewModel

@Composable
fun MainNavigation() {
  val backStack = rememberNavBackStack(Timer)
  val context = LocalContext.current
  val application = context.applicationContext as PomodoroApplication
  
  val repository = application.container.pomodoroRepository
  val settingsRepository = application.container.settingsRepository

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider =
      entryProvider {
        entry<Timer> {
          val viewModel: TimerViewModel = viewModel(
              factory = TimerViewModel.provideFactory(repository, settingsRepository)
          )
          TimerScreen(
              viewModel = viewModel,
              onNavigateToHistory = { backStack.add(History) },
              onNavigateToSettings = { backStack.add(Settings) }
          )
        }
        entry<History> {
          val viewModel: HistoryViewModel = viewModel(
              factory = HistoryViewModel.provideFactory(repository)
          )
          HistoryScreen(viewModel = viewModel)
        }
        entry<Settings> {
          val viewModel: SettingsViewModel = viewModel(
              factory = SettingsViewModel.provideFactory(settingsRepository)
          )
          SettingsScreen(viewModel = viewModel)
        }
      },
  )
}
