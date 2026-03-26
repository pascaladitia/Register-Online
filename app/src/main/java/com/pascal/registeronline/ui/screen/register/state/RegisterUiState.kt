package com.pascal.registeronline.ui.screen.register.state

data class RegisterUiState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val isRegister: Boolean = false,

    val fullName: Pair<String, Boolean> = "" to false,
    val email: Pair<String, Boolean> = "" to false,
    val emailErrorMessage: String = "",
    val password: Pair<String, Boolean> = "" to false,
    val phone: String = "",
    val isPasswordVisible: Boolean = false,
)