package com.pascal.registeronline.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.button.ButtonComponent
import com.pascal.registeronline.ui.component.form.FormEmailComponent
import com.pascal.registeronline.ui.component.form.FormPasswordComponent
import com.pascal.registeronline.ui.component.screenUtils.GradientHeader
import com.pascal.registeronline.ui.screen.login.state.LocalLoginEvent
import com.pascal.registeronline.ui.screen.login.state.LoginUiState
import com.pascal.registeronline.ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState = LoginUiState()
) {
    val event = LocalLoginEvent.current
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isUserError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {
        GradientHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Masuk ke Akun Verifikator",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Masukkan email dan password untuk masuk",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                FormEmailComponent(
                    title = stringResource(R.string.label_email),
                    hintText = stringResource(R.string.hint_email),
                    value = user,
                    isShowTitle = true,
                    onValueChange = {
                        user = it
                        isUserError = false
                    },
                    isError = isUserError
                )

                Spacer(modifier = Modifier.height(16.dp))

                FormPasswordComponent(
                    title = stringResource(R.string.label_password),
                    hintText = stringResource(R.string.hint_password),
                    value = password,
                    isShowTitle = true,
                    onValueChange = {
                        password = it
                        isPasswordError = false
                    },
                    isError = isPasswordError,
                    isPasswordVisible = isPasswordVisible,
                    onIconClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    if (user.isBlank()) {
                        isUserError = true
                    }
                    if (password.isBlank()) {
                        isPasswordError = true
                    }

                    if (user.isNotBlank() && password.isNotBlank()) {
                        event.onLogin(user, password)
                        keyboardController?.hide()
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                ButtonComponent(
                    text = "Login"
                ) {
                    if (user.isBlank()) {
                        isUserError = true
                    }
                    if (password.isBlank()) {
                        isPasswordError = true
                    }

                    if (user.isNotBlank() && password.isNotBlank()) {
                        event.onLogin(user, password)
                        keyboardController?.hide()
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Belum punya akun?",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Klik Bantuan",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPreview() {
    AppTheme {
        LoginScreen()
    }
}