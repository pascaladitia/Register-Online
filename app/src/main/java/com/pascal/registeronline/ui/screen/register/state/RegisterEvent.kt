package com.pascal.registeronline.ui.screen.register.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalRegisterEvent = compositionLocalOf { RegisterEvent() }

@Stable
data class RegisterEvent(
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onFullNameChange: (String) -> Unit = {},
    val onPhoneChange: (String) -> Unit = {},
    val onTogglePasswordVisibility: () -> Unit = {},
    val onSubmit: () -> Unit = {}
)