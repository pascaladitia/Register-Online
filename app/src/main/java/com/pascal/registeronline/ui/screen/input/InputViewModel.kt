package com.pascal.registeronline.ui.screen.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.registeronline.data.local.entity.DraftEntity
import com.pascal.registeronline.domain.usecase.local.LocalUseCase
import com.pascal.registeronline.ui.screen.input.state.InputUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InputViewModel(
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState = _uiState.asStateFlow()

    private fun update(block: InputUiState.() -> InputUiState) {
        _uiState.update(block)
    }

    fun setName(v: String) = update { copy(name = v, nameError = false) }
    fun setNik(v: String) = update { copy(nik = v, nikError = false) }
    fun setPhone(v: String) = update { copy(phone = v, phoneError = false) }
    fun setBirthPlace(v: String) = update { copy(birthPlace = v) }
    fun setBirthDate(v: String) = update { copy(birthDate = v) }

    fun setAddress(v: String) = update { copy(address = v) }
    fun setProvinsi(v: String) = update { copy(provinsi = v) }
    fun setKota(v: String) = update { copy(kotaKabupaten = v) }
    fun setKecamatan(v: String) = update { copy(kecamatan = v) }
    fun setKelurahan(v: String) = update { copy(kelurahan = v) }
    fun setKodePos(v: String) = update { copy(kodePos = v) }

    fun setSameAddress(v: Boolean) = update {
        copy(
            isSameAddress = v,
            alamatDomisili = if (v) address else alamatDomisili
        )
    }

    fun setDomisili(v: String) = update { copy(alamatDomisili = v) }

    fun openGender() = update { copy(isGenderSheet = true to isStatusSheet.second) }
    fun openStatus() = update { copy(isStatusSheet = true to isStatusSheet.second) }
    fun dismissStatus() = update { copy(isStatusSheet = false to -1) }
    fun dismissGender() = update { copy(isGenderSheet = false to -1) }

    fun selectGender(index: Int, value: String) =
        update { copy(gender = value, isGenderSheet = false to index) }

    fun selectStatus(index: Int, value: String) =
        update { copy(status = value, isStatusSheet = false to index) }

    fun openPekerjaan() = update { copy(isPekerjaanSheet = true to isPekerjaanSheet.second) }
    fun dismissPekerjaan() = update { copy(isPekerjaanSheet = false to -1) }

    fun selectPekerjaan(index: Int, value: String) =
        update { copy(occupation = value, isPekerjaanSheet = false to index) }

    fun openCameraPrimary() = update {
        copy(openCameraPrimary = true, isPrimaryCamera = true)
    }

    fun openCameraSecondary() = update {
        copy(openCameraSecondary = true, isPrimaryCamera = false)
    }

    fun onCapture(path: String) = update {
        copy(
            previewImage = path,
            isPreview = true,
            openCameraPrimary = false,
            openCameraSecondary = false
        )
    }

    fun retakePhoto() = update {
        copy(
            isPreview = false,
            openCameraPrimary = isPrimaryCamera,
            openCameraSecondary = !isPrimaryCamera
        )
    }

    fun confirmPhoto() = update {
        if (isPrimaryCamera) {
            copy(
                ktpFile = previewImage,
                isPreview = false
            )
        } else {
            copy(
                ktpFileSecondary = previewImage,
                isPreview = false
            )
        }
    }

    private fun validate(): Boolean {
        val s = uiState.value
        var valid = true

        if (s.name.isEmpty()) {
            update { copy(nameError = true) }
            valid = false
        }
        if (s.nik.isEmpty()) {
            update { copy(nikError = true) }
            valid = false
        }
        if (s.phone.isEmpty()) {
            update { copy(phoneError = true) }
            valid = false
        }
        if (s.ktpFile.isEmpty()) {
            update { copy(error = true to "Foto KTP wajib") }
            valid = false
        }

        return valid
    }

    fun saveDraft() = viewModelScope.launch {
        if (!validate()) return@launch

        val s = uiState.value

        localUseCase.insertDraft(
            DraftEntity(
                name = s.name,
                nik = s.nik,
                phone = s.phone,
                birthPlace = s.birthPlace,
                birthDate = s.birthDate,
                status = s.status,
                occupation = s.occupation,
                address = s.address,
                provinsi = s.provinsi,
                kotaKabupaten = s.kotaKabupaten,
                kecamatan = s.kecamatan,
                kelurahan = s.kelurahan,
                kodePos = s.kodePos,
                alamatDomisili = if (s.isSameAddress) s.address else s.alamatDomisili,
                provinsiDomisili = "",
                kotaKabupatenDomisili = "",
                kecamatanDomisili = "",
                kelurahanDomisili = "",
                kodePosDomisili = "",
                ktpFile = s.ktpFile,
                ktpFileSecondary = s.ktpFileSecondary
            )
        )

        update { copy(isSuccess = true) }
    }

    fun hideDialog() = update { copy(error = false to "") }
}