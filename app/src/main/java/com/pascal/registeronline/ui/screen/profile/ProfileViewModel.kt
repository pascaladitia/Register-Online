package com.pascal.registeronline.ui.screen.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import com.pascal.registeronline.ui.screen.profile.state.ProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val app: Application
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState: StateFlow<ProfileUIState> = _uiState.asStateFlow()

    fun hideDialog() {
        _uiState.update { it.copy(error = false to "") }
    }
}