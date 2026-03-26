package com.pascal.registeronline.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.login.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val remoteUseCase: RemoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, isEmailError = false) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, isPasswordError = false) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onSubmit() {
        val state = _uiState.value
        val isEmailBlank = state.email.isBlank()
        val isPasswordBlank = state.password.isBlank()

        if (isEmailBlank || isPasswordBlank) {
            _uiState.update {
                it.copy(
                    isEmailError = isEmailBlank,
                    isPasswordError = isPasswordBlank
                )
            }
            return
        }

        loadLogin(state.email, state.password)
    }

    private fun loadLogin(email: String, password: String) {
        _uiState.update { it.copy(isLoading = true) }

        val body = LoginBody(email = email, password = password)

        viewModelScope.launch {
            remoteUseCase.login(body)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to e.message.orEmpty()
                        )
                    }
                }
                .collect {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLogin = true
                        )
                    }
                }
        }
    }

    fun showDialog(msg: String) {
        _uiState.update { it.copy(error = true to msg) }
    }

    fun hideDialog() {
        _uiState.update { it.copy(error = false to "") }
    }
}