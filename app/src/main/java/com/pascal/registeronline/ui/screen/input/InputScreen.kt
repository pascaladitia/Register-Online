package com.pascal.registeronline.ui.screen.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.form.FormBasicComponent
import com.pascal.registeronline.ui.component.form.FormClickedComponent
import com.pascal.registeronline.ui.screen.input.state.InputUiState
import com.pascal.registeronline.ui.screen.input.state.LocalInputEvent
import com.pascal.registeronline.ui.theme.AppTheme
import com.pascal.registeronline.utils.setMandatoryTitle

@Composable
fun InputScreen(
    uiState: InputUiState = InputUiState()
) {
    val event = LocalInputEvent.current

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .systemBarsPadding()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { event.onBack() },
                    imageVector = Icons.AutoMirrored.Filled.KeyboardBackspace,
                    contentDescription = null
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = stringResource(R.string.label_add_data),
                    style = MaterialTheme.typography.titleMedium
                )

            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.label_primary_data),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary.copy(0.2f),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { event.onBack() },
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = stringResource(R.string.message_prepare_input_data),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

            }

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = setMandatoryTitle(stringResource(R.string.label_handphone)),
                hintText = stringResource(R.string.hint_handphone_number),
                value = uiState.phone,
                onValueChange = event.setPhone,
                isError = uiState.phoneError,
                isNumber = true
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = setMandatoryTitle(stringResource(R.string.label_nik)),
                hintText = stringResource(R.string.hint_nik),
                value = uiState.nik,
                onValueChange = event.setNik,
                isError = uiState.nikError,
                isNumber = true
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = setMandatoryTitle(stringResource(R.string.label_photo_ktp)),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.message_prepare_take_ktp),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(8.dp))

            Row {
                ImagePicker(
                    path = uiState.ktpFile,
                    onClick = event.openCameraPrimary
                )

                Spacer(Modifier.width(8.dp))

                ImagePicker(
                    path = uiState.ktpFileSecondary,
                    onClick = event.openCameraSecondary
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.label_more_info),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_name)),
                hintText = stringResource(R.string.hint_name),
                value = uiState.name,
                onValueChange = event.setName,
                isError = uiState.nameError
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_place_of_birth)),
                hintText = stringResource(R.string.hint_place_of_birth),
                value = uiState.birthPlace,
                onValueChange = event.setBirthPlace,
                isError = false
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_date_birth)),
                hintText = stringResource(R.string.hint_date_birth),
                value = uiState.birthDate,
                onValueChange = event.setBirthDate,
                isError = false
            )

            Spacer(Modifier.height(16.dp))

            FormClickedComponent(
                title = AnnotatedString(stringResource(R.string.label_gender)),
                hintText = stringResource(R.string.label_choose_gender),
                value = uiState.gender,
                onIconClick = event.openGender
            )

            Spacer(Modifier.height(16.dp))

            FormClickedComponent(
                title = AnnotatedString(stringResource(R.string.label_status)),
                hintText = stringResource(R.string.label_choose_status),
                value = uiState.status,
                onIconClick = event.openStatus
            )

            Spacer(Modifier.height(16.dp))

            FormClickedComponent(
                title = AnnotatedString(stringResource(R.string.label_job)),
                hintText = stringResource(R.string.label_choose_job),
                value = uiState.occupation,
                onIconClick = event.openPekerjaan
            )
        }

        item {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.label_information_address_ktp),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_address)),
                hintText = stringResource(R.string.hint_address),
                value = uiState.address,
                onValueChange = event.setAddress,
                isError = false
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_province)),
                hintText = stringResource(R.string.hint_province),
                value = uiState.provinsi,
                onValueChange = event.setProvinsi,
                isError = false
            )

            Spacer(Modifier.height(16.dp))
            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_regency)),
                hintText = stringResource(R.string.hint_regency),
                value = uiState.kotaKabupaten,
                onValueChange = event.setKota,
                isError = false
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_subdistrict)),
                hintText = stringResource(R.string.hint_subdistrict),
                value = uiState.kecamatan,
                onValueChange = event.setKecamatan,
                isError = false
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_district)),
                hintText = stringResource(R.string.hint_district),
                value = uiState.kelurahan,
                onValueChange = event.setKelurahan,
                isError = false
            )

            Spacer(Modifier.height(16.dp))

            FormBasicComponent(
                title = AnnotatedString(stringResource(R.string.label_postal_code)),
                hintText = stringResource(R.string.hint_postal_code),
                value = uiState.kodePos,
                onValueChange = event.setKodePos,
                isError = false,
                isNumber = true
            )
        }

        item {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.label_address_domicily),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.isSameAddress,
                    onCheckedChange = event.setSameAddress
                )

                Text(
                    text = stringResource(R.string.message_domicili),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            if (!uiState.isSameAddress) {
                FormBasicComponent(
                    title = AnnotatedString(stringResource(R.string.label_address_domicili)),
                    hintText = "",
                    value = uiState.alamatDomisili,
                    onValueChange = event.setDomisili,
                    isError = false
                )
            }
        }

        item {
            Spacer(Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = event.saveDraft
            ) {
                Text(stringResource(R.string.label_save_as_draft))
            }
        }
    }
}

@Composable
fun RowScope.ImagePicker(
    path: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(120.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (path.isEmpty()) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.CameraAlt,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            AsyncImage(
                model = path,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun InputPreview() {
    AppTheme {
        InputScreen()
    }
}