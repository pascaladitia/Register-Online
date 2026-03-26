@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.registeronline.ui.screen.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.lifecycle.ViewModel
import com.pascal.registeronline.domain.usecase.local.LocalUseCase
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.home.state.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val remoteUseCase: RemoteUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

}
