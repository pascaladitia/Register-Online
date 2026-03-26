package com.pascal.registeronline.ui.screen.login.state

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",

    val isLogin: Boolean = false
)