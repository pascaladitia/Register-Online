package com.pascal.registeronline.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.registeronline.data.local.entity.DraftEntity
import com.pascal.registeronline.domain.usecase.local.LocalUseCase
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.ui.screen.home.state.HomeUiState
import com.pascal.registeronline.utils.ImageCompressor
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.streams.asInput
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

    fun uploadSingleDraft(id: Int) {
        viewModelScope.launch {

            val drafts = localUseCase.getDraft() ?: emptyList()
            val draft = drafts.find { it.id == id }

            if (draft == null) {
                _uiState.update {
                    it.copy(error = true to "Draft tidak ditemukan")
                }
                return@launch
            }

            _uiState.update { it.copy(isLoading = true) }

            try {
                val body = createMultipartFromDraft(draft)

                remoteUseCase.syncData(body)
                    .catch { e ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = true to e.message.orEmpty()
                            )
                        }
                    }
                    .collect {
                        localUseCase.deleteDraft(draft)

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                isUploadSuccess = true
                            )
                        }

                        loadDrafts()
                        loadMembers()
                    }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = true to e.message.orEmpty()
                    )
                }
            }
        }
    }

    private fun uploadAll() {
        viewModelScope.launch {

            val drafts = localUseCase.getDraft() ?: emptyList()

            if (drafts.isEmpty()) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = true to "Tidak ada data draft"
                    )
                }
                return@launch
            }

            _uiState.update { it.copy(isLoading = true) }

            drafts.forEach { draft ->

                try {
                    val body = createMultipartFromDraft(draft)

                    remoteUseCase.syncData(body)
                        .catch { e ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = true to e.message.orEmpty()
                                )
                            }
                        }
                        .collect {
                            localUseCase.deleteDraft(draft)
                        }

                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to e.message.orEmpty()
                        )
                    }
                    return@launch
                }
            }

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

    fun createMultipartFromDraft(
        draft: DraftEntity
    ): MultiPartFormDataContent {

        val ktpPrimary = ImageCompressor.compressToMax2MB(draft.ktpFile)
        val ktpSecondary = ImageCompressor.compressToMax2MB(draft.ktpFileSecondary)

        val parts = formData {

            append("name", draft.name)
            append("nik", draft.nik)
            append("phone", draft.phone)
            append("birth_place", draft.birthPlace)
            append("birth_date", draft.birthDate)
            append("status", draft.status)
            append("occupation", draft.occupation)

            append("address", draft.address)
            append("provinsi", draft.provinsi)
            append("kota_kabupaten", draft.kotaKabupaten)
            append("kecamatan", draft.kecamatan)
            append("kelurahan", draft.kelurahan)
            append("kode_pos", draft.kodePos)

            append("alamat_domisili", draft.alamatDomisili)
            append("provinsi_domisili", draft.provinsiDomisili)
            append("kota_kabupaten_domisili", draft.kotaKabupatenDomisili)
            append("kecamatan_domisili", draft.kecamatanDomisili)
            append("kelurahan_domisili", draft.kelurahanDomisili)
            append("kode_pos_domisili", draft.kodePosDomisili)

            if (ktpPrimary.exists()) {
                appendInput(
                    key = "ktp_file",
                    headers = Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=\"${ktpPrimary.name}\"")
                    },
                    size = ktpPrimary.length()
                ) {
                    ktpPrimary.inputStream().asInput()
                }
            }

            if (ktpSecondary.exists()) {
                appendInput(
                    key = "ktp_file_secondary",
                    headers = Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=\"${ktpSecondary.name}\"")
                    },
                    size = ktpSecondary.length()
                ) {
                    ktpSecondary.inputStream().asInput()
                }
            }
        }

        return MultiPartFormDataContent(parts)
    }

    fun dismissUploadSuccess() {
        _uiState.update { it.copy(isUploadSuccess = false) }
    }

    fun hideDialog() {
        _uiState.update { it.copy(error = false to "") }
    }
}