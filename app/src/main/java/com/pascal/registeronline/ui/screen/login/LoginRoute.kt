package com.pascal.registeronline.ui.screen.login

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.dialog.ShowDialog
import com.pascal.registeronline.ui.component.screenUtils.LoadingScreen
import com.pascal.registeronline.ui.component.screenUtils.hasLocationPermission
import com.pascal.registeronline.ui.screen.login.state.LocalLoginEvent
import org.koin.androidx.compose.koinViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoginRoute(
    viewModel: LoginViewModel = koinViewModel(),
    onRegister: () -> Unit,
    onLogin: () -> Unit
) {
    val context = LocalContext.current
    val event = LocalLoginEvent.current

    val uiState by viewModel.uiState.collectAsState()

    BackHandler {
        (context as? ComponentActivity)?.finish()
    }

    val multiplePermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.CAMERA
            )
        )
    } else {
        rememberMultiplePermissionsState(
                permissions = listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
            )
        )
    }

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    LaunchedEffect(uiState.isLogin) {
        if (uiState.isLogin) onLogin()
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
        LocalLoginEvent provides event.copy(
            onLogin = { email, password ->
                if (!context.hasLocationPermission()) {
                    viewModel.showDialog(context.getString(R.string.message_permission_allow))
                } else {
                    viewModel.loadLogin(email, password)
                }
            }
        )
    ) {
        LoginScreen(
            uiState = uiState
        )
    }
}