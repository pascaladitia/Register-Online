package com.pascal.registeronline.ui.screen.input

import android.app.Application
import androidx.lifecycle.ViewModel
import com.pascal.registeronline.domain.usecase.local.LocalUseCase
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.input.state.InputUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InputViewModel(
    private val context: Application,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState.asStateFlow()


    fun showDialog(msg: String) {
        _uiState.update { it.copy(error = true to msg) }
    }

    fun hideDialog() {
        _uiState.update { it.copy(error = false to "") }
    }
}