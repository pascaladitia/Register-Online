package com.pascal.registeronline.ui.screen.profile.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalProfileEvent = compositionLocalOf { ProfileEvent() }

@Stable
data class ProfileEvent(
    val onNavBack: () -> Unit = {},
    val onLogout: () -> Unit = {}
)