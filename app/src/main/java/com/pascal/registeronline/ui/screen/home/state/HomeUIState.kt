package com.pascal.registeronline.ui.screen.home.state

data class HomeUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to ""
)