package com.pascal.registeronline.ui.screen.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.dialog.ShowDialog
import com.pascal.registeronline.ui.component.screenUtils.SelectedBottomSheet
import com.pascal.registeronline.ui.screen.input.component.CameraScreen
import com.pascal.registeronline.ui.screen.input.component.PreviewKtpScreen
import com.pascal.registeronline.ui.screen.input.state.InputEvent
import com.pascal.registeronline.ui.screen.input.state.LocalInputEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun InputRoute(
    viewModel: InputViewModel = koinViewModel(),
    onNavBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val event = InputEvent(
        onBack = onNavBack,
        setName = viewModel::setName,
        setNik = viewModel::setNik,
        setPhone = viewModel::setPhone,
        setBirthPlace = viewModel::setBirthPlace,
        setBirthDate = viewModel::setBirthDate,
        setAddress = viewModel::setAddress,
        setProvinsi = viewModel::setProvinsi,
        setKota = viewModel::setKota,
        setKecamatan = viewModel::setKecamatan,
        setKelurahan = viewModel::setKelurahan,
        setKodePos = viewModel::setKodePos,
        setSameAddress = viewModel::setSameAddress,
        setDomisili = viewModel::setDomisili,
        openGender = viewModel::openGender,
        openStatus = viewModel::openStatus,
        dismissGender = viewModel::dismissGender,
        dismissStatus = viewModel::dismissStatus,
        selectGender = viewModel::selectGender,
        selectStatus = viewModel::selectStatus,
        openPekerjaan = viewModel::openPekerjaan,
        dismissPekerjaan = viewModel::dismissPekerjaan,
        selectPekerjaan = viewModel::selectPekerjaan,
        openCameraPrimary = viewModel::openCameraPrimary,
        openCameraSecondary = viewModel::openCameraSecondary,
        saveDraft = viewModel::saveDraft
    )

    if (uiState.isGenderSheet.first) {
        SelectedBottomSheet(
            title = stringResource(R.string.label_gender),
            data = uiState.genderList,
            selectedIndex = uiState.isGenderSheet.second,
            itemText = { it.orEmpty() },
            onSelect = { item, index ->
                event.selectGender(index, item!!)
            },
            onDismiss = event.dismissGender
        )
    }

    if (uiState.isStatusSheet.first) {
        SelectedBottomSheet(
            title = stringResource(R.string.label_status),
            data = uiState.statusList,
            selectedIndex = uiState.isStatusSheet.second,
            itemText = { it.orEmpty() },
            onSelect = { item, index ->
                event.selectStatus(index, item!!)
            },
            onDismiss = event.dismissStatus
        )
    }

    if (uiState.isPekerjaanSheet.first) {
        SelectedBottomSheet(
            title = stringResource(R.string.label_job),
            data = uiState.pekerjaanList,
            selectedIndex = uiState.isPekerjaanSheet.second,
            itemText = { it.orEmpty() },
            onSelect = { item, index ->
                event.selectPekerjaan(index, item!!)
            },
            onDismiss = event.dismissPekerjaan
        )
    }

    if (uiState.error.first) {
        ShowDialog(
            message = uiState.error.second,
            textButton = stringResource(R.string.label_close)
        ) {
            viewModel.hideDialog()
        }
    }

    if (uiState.isSuccess) {
        ShowDialog(
            message = stringResource(R.string.message_save_draft_success),
            textButton = stringResource(R.string.label_back)
        ) {
            onNavBack()
        }
    }

    CompositionLocalProvider(
        LocalInputEvent provides event
    ) {
        Box {
            InputScreen(uiState = uiState)

            if (uiState.openCameraPrimary || uiState.openCameraSecondary) {
                CameraScreen(
                    onResult = { viewModel.onCapture(it) },
                    onBack = { viewModel.retakePhoto() }
                )
            }

            if (uiState.isPreview) {
                PreviewKtpScreen(
                    imagePath = uiState.previewImage,
                    onUse = { viewModel.confirmPhoto() },
                    onRetake = { viewModel.retakePhoto() }
                )
            }
        }
    }
}