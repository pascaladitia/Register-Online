package com.pascal.registeronline.ui.screen.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.dialog.ShowDialog
import com.pascal.registeronline.ui.component.screenUtils.LoadingScreen
import com.pascal.registeronline.ui.screen.home.state.HomeEvent
import com.pascal.registeronline.ui.screen.home.state.LocalHomeEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel(),
    onAddData: () -> Unit,
    onEditDraft: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isUploadSuccess) {
        if (uiState.isUploadSuccess) {
            viewModel.dismissUploadSuccess()
        }
    }

    if (uiState.isLoading) {
        LoadingScreen()
    }

    if (uiState.error.first) {
        ShowDialog(
            message = uiState.error.second,
            textButton = stringResource(R.string.label_close),
            color = MaterialTheme.colorScheme.primary
        ) {
            viewModel.hideDialog()
        }
    }

    CompositionLocalProvider(
        LocalHomeEvent provides HomeEvent(
            onTabSelected = viewModel::onTabSelected,
            onAddData = onAddData,
            onEditDraft = onEditDraft,
            onUploadDraft = { /* TODO: single upload */ },
            onShowUploadAllDialog = viewModel::onShowUploadAllDialog,
            onDismissUploadAllDialog = viewModel::onDismissUploadAllDialog,
            onConfirmUploadAll = viewModel::onConfirmUploadAll,
            onDismissError = viewModel::hideDialog,
        )
    ) {
        HomeScreen(uiState = uiState)
    }
}