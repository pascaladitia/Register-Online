package com.pascal.registeronline.ui.screen.profile

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
import com.pascal.registeronline.ui.screen.profile.component.LogoutDialog
import com.pascal.registeronline.ui.screen.profile.state.LocalProfileEvent
import com.pascal.registeronline.ui.screen.profile.state.ProfileEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel(),
    onNavBack: () -> Unit,
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    LaunchedEffect(uiState.isLogoutSuccess) {
        if (uiState.isLogoutSuccess) onLogout()
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

    if (uiState.logoutDialog) {
        LogoutDialog(
            onConfirm = {
                viewModel.setLogoutDialog(false)
                viewModel.loadLogout()
            },
            onDismiss = {
                viewModel.setLogoutDialog(false)
            }
        )
    }

    CompositionLocalProvider(
        LocalProfileEvent provides ProfileEvent(
            onNavBack = onNavBack,
            onLogout = { viewModel.setLogoutDialog(true) }
        )
    ) {
        ProfileScreen(
            uiState = uiState
        )
    }
}

