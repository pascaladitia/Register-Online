package com.pascal.registeronline.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.registeronline.R
import com.pascal.registeronline.data.prefs.PreferencesLogin
import com.pascal.registeronline.ui.component.dialog.ShowDialog
import com.pascal.registeronline.ui.component.screenUtils.LoadingScreen
import com.pascal.registeronline.ui.screen.profile.state.LocalProfileEvent
import com.pascal.registeronline.ui.screen.profile.state.ProfileEvent
import com.pascal.registeronline.ui.screen.profile.state.ProfileUIState
import com.pascal.registeronline.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel(),
    onNavBack: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
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
        LocalProfileEvent provides ProfileEvent(
            onNavBack = onNavBack,
            onLogout = {
                PreferencesLogin.clear(context)
                onLogout()
            }
        )
    ) {
        ProfileScreen(
            uiState = uiState
        )
    }
}

