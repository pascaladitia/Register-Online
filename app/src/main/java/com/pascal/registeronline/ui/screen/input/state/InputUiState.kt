package com.pascal.registeronline.ui.screen.input.state

data class InputUiState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",

    val isInputSuccess: Boolean = false
)