# PomodoroTec V2

App de productividad para Android basada en el método Pomodoro, construida con Jetpack Compose y las últimas librerías de Android.

> Desarrollada como proyecto de demostración para la charla **"Tu próxima app Android la construyes conversando: Android Skills en acción"** — Google Developer Group Local, Mayo 2026.

---

## ¿Qué es el método Pomodoro?

Una técnica de productividad simple:

1. Trabaja con concentración durante **25 minutos**
2. Toma un descanso de **5 minutos**
3. Repite el ciclo
4. Cada 4 sesiones, toma un descanso largo de **15 minutos**

---

## Pantallas

| Timer | Historial | Configuración |
|---|---|---|
| Cuenta regresiva con progreso circular, alternancia automática entre sesiones de trabajo y descanso | Lista de todas las sesiones completadas guardadas localmente | Duración de sesiones personalizable con sliders |

---

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| UI | Jetpack Compose + Material Design 3 |
| Navegación | Navigation 3 |
| Base de datos | Room + KSP |
| Preferencias | DataStore |
| Arquitectura | MVVM con repositorios |
| Lenguaje | Kotlin 2.3 |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 36 (Android 15) |

---

## Estructura del proyecto

```
app/src/main/java/com/gdg/pomodorotec/
├── data/
│   ├── AppDatabase.kt
│   ├── PomodoroSession.kt          ← modelo Room (WORK, SHORT_BREAK, LONG_BREAK)
│   ├── PomodoroSessionDao.kt
│   ├── PomodoroRepository.kt
│   └── settings/
│       └── SettingsRepository.kt   ← duraciones vía DataStore
├── ui/
│   ├── timer/                      ← TimerScreen + TimerViewModel
│   ├── history/                    ← HistoryScreen + HistoryViewModel
│   └── settings/                   ← SettingsScreen + SettingsViewModel
├── theme/
├── Navigation.kt
├── NavigationKeys.kt
└── PomodoroApplication.kt
```

---

## Cómo correr el proyecto

### Requisitos

- Android Studio Ladybug o superior
- JDK 17
- Android SDK con API 36

### Pasos

```bash
git clone https://github.com/Me7aBen/PomodoroTec-V2.git
cd PomodoroTec-V2
```

Abre la carpeta en Android Studio y presiona **Run**.

---

## Contexto del codelab

Este proyecto es el resultado final del codelab **"Tu primera app Android construida conversando: PomodoroTec"**, donde se construye esta app paso a paso usando Gemini CLI y Android Skills.

- **Codelab**: [me7aben.github.io/gdg-codelabs](https://me7aben.github.io/gdg-codelabs/pomodorotec-v2/?index=gdg-codelabs)
- **Sitio de codelabs del GDG**: [me7aben.github.io/gdg-codelabs](https://me7aben.github.io/gdg-codelabs/)
