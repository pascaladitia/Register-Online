package com.pascal.registeronline.ui.screen.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.registeronline.data.prefs.PreferencesLogin
import com.pascal.registeronline.data.remote.dtos.profile.ProfileBody
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.profile.state.ProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val context: Application,
    private val remoteUseCase: RemoteUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState: StateFlow<ProfileUIState> = _uiState.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            val body = ProfileBody(
                email = PreferencesLogin.getEmail(context),
                password = PreferencesLogin.getPassword(context)
            )

            remoteUseCase.profile(body)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to e.message.orEmpty()
                        )
                    }
                }
                .collect { members ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            profile = members
                        )
                    }
                }
        }
    }

    fun hideDialog() {
        _uiState.update { it.copy(error = false to "") }
    }
}