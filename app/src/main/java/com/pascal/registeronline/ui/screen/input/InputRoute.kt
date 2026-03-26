package com.pascal.registeronline.ui.screen.input

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.dialog.ShowDialog
import com.pascal.registeronline.ui.component.screenUtils.LoadingScreen
import com.pascal.registeronline.ui.screen.input.state.InputEvent
import com.pascal.registeronline.ui.screen.input.state.LocalInputEvent
import org.koin.androidx.compose.koinViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun InputRoute(
    viewModel: InputViewModel = koinViewModel(),
    onNavBack: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isInputSuccess) {
        if (uiState.isInputSuccess) onNavBack()
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
        LocalInputEvent provides InputEvent(
          onNavBack = onNavBack
        )
    ) {
        InputScreen(uiState = uiState)
    }
}