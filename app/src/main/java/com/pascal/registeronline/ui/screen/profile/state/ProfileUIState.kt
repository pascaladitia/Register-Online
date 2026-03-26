package com.pascal.registeronline.ui.screen.profile.state

data class ProfileUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to ""
)