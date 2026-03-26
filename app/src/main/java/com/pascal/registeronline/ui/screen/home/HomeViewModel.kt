package com.pascal.registeronline.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.registeronline.domain.usecase.local.LocalUseCase
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val remoteUseCase: RemoteUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDrafts()
    }

    fun onTabSelected(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
        if (index == 1) loadMembers()
    }

    fun loadDrafts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val drafts = localUseCase.getDraft() ?: emptyList()
            _uiState.update { it.copy(isLoading = false, drafts = drafts) }
        }
    }

    private fun loadMembers() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            remoteUseCase.getMember()
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
                            members = members
                        )
                    }
                }
        }
    }

    fun onShowUploadAllDialog() {
        _uiState.update { it.copy(isUploadAllDialogVisible = true) }
    }

    fun onDismissUploadAllDialog() {
        _uiState.update { it.copy(isUploadAllDialogVisible = false) }
    }

    fun onConfirmUploadAll() {
        _uiState.update { it.copy(isUploadAllDialogVisible = false) }
        uploadAll()
    }

    private fun uploadAll() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // TODO: implement upload all logic per draft
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isUploadSuccess = true,
                    selectedTab = 1
                )
            }
            loadMembers()
        }
    }

    fun dismissUploadSuccess() {
        _uiState.update { it.copy(isUploadSuccess = false) }
    }

    fun hideDialog() {
        _uiState.update { it.copy(error = false to "") }
    }
}