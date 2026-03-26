package com.pascal.registeronline.ui.screen.register

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.register.state.RegisterUiState
import com.pascal.registeronline.utils.showToast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val context: Application,
    private val remoteUseCase: RemoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onFullNameChange(value: String) {
        _uiState.update { it.copy(fullName = value to false) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value to false, emailErrorMessage = "") }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value to false) }
    }

    fun onPhoneChange(value: String) {
        _uiState.update { it.copy(phone = value.filter { c -> c.isDigit() }) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onSubmit() {
        val state = _uiState.value

        val isFullNameBlank = state.fullName.first.isBlank()
        val isEmailBlank = state.email.first.isBlank()
        val isEmailInvalid = !isEmailBlank && !Patterns.EMAIL_ADDRESS.matcher(state.email.first).matches()
        val isPasswordBlank = state.password.first.isBlank()

        if (isFullNameBlank || isEmailBlank || isEmailInvalid || isPasswordBlank) {
            _uiState.update {
                it.copy(
                    fullName = it.fullName.first to isFullNameBlank,
                    email = it.email.first to (isEmailBlank || isEmailInvalid),
                    emailErrorMessage = when {
                        isEmailBlank -> "Email tidak boleh kosong"
                        isEmailInvalid -> "Format email tidak valid"
                        else -> ""
                    },
                    password = it.password.first to isPasswordBlank
                )
            }
            return
        }

        loadRegister(
            email = state.email.first,
            password = state.password.first,
            fullName = state.fullName.first,
            phone = state.phone.ifBlank { null }
        )
    }

    private fun loadRegister(
        email: String,
        password: String,
        fullName: String,
        phone: String?
    ) {
        _uiState.update { it.copy(isLoading = true) }

        val body = RegisterBody(
            email = email,
            password = password,
            full_name = fullName,
            phone = phone
        )

        viewModelScope.launch {
            remoteUseCase.register(body)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to e.message.orEmpty()
                        )
                    }
                }
                .collect {
                    showToast(context, it.message)

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRegister = true
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