package com.pascal.registeronline.ui.screen.register

import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.button.ButtonComponent
import com.pascal.registeronline.ui.component.form.FormBasicComponent
import com.pascal.registeronline.ui.component.form.FormEmailComponent
import com.pascal.registeronline.ui.component.form.FormPasswordComponent
import com.pascal.registeronline.ui.component.screenUtils.GradientHeader
import com.pascal.registeronline.ui.screen.register.state.LocalRegisterEvent
import com.pascal.registeronline.ui.screen.register.state.RegisterUiState
import com.pascal.registeronline.ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState = RegisterUiState()
) {
    val event = LocalRegisterEvent.current
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
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                text = stringResource(R.string.label_title_register),
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.label_subtitle_register),
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
                FormBasicComponent(
                    title = buildAnnotatedString {
                        append(stringResource(R.string.label_full_name))
                        withStyle(SpanStyle(color = Red)) { append(" *") }
                    },
                    hintText = stringResource(R.string.hint_full_name),
                    value = uiState.fullName.first,
                    onValueChange = event.onFullNameChange,
                    isError = uiState.fullName.second,
                    enabled = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                FormEmailComponent(
                    title = stringResource(R.string.label_email),
                    hintText = stringResource(R.string.hint_email),
                    value = uiState.email.first,
                    isShowTitle = true,
                    isMandatory = true,
                    onValueChange = event.onEmailChange,
                    isError = uiState.email.second
                )

                Spacer(modifier = Modifier.height(16.dp))

                FormPasswordComponent(
                    title = stringResource(R.string.label_password),
                    hintText = stringResource(R.string.hint_password),
                    value = uiState.password.first,
                    isShowTitle = true,
                    isMandatory = true,
                    onValueChange = event.onPasswordChange,
                    isError = uiState.password.second,
                    isPasswordVisible = uiState.isPasswordVisible,
                    onIconClick = event.onTogglePasswordVisibility
                ) {
                    keyboardController?.hide()
                    event.onSubmit()
                }

                Spacer(modifier = Modifier.height(16.dp))

                FormBasicComponent(
                    title = buildAnnotatedString {
                        append(stringResource(R.string.label_phone))
                    },
                    hintText = stringResource(R.string.hint_phone),
                    value = uiState.phone,
                    onValueChange = event.onPhoneChange,
                    isError = false,
                    isNumber = true,
                    enabled = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonComponent(text = stringResource(R.string.label_register)) {
                    keyboardController?.hide()
                    event.onSubmit()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterPreview() {
    AppTheme {
        RegisterScreen()
    }
}