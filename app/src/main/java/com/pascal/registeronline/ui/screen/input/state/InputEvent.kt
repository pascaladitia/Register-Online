package com.pascal.registeronline.ui.screen.input.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalInputEvent = compositionLocalOf { InputEvent() }

@Stable
data class InputEvent(
    val onNavBack: () -> Unit = {}
)