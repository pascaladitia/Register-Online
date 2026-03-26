package com.pascal.registeronline.ui.screen.login.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalLoginEvent = compositionLocalOf { LoginEvent() }

@Stable
data class LoginEvent(
    val onRegister: () -> Unit = {},
    val onLogin: (String, String) -> Unit = { _, _ -> }
)