package com.pascal.registeronline.ui.screen.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pascal.registeronline.ui.component.form.FormBasicComponent
import com.pascal.registeronline.ui.component.form.FormClickedComponent
import com.pascal.registeronline.ui.screen.input.state.InputUiState
import com.pascal.registeronline.ui.screen.input.state.LocalInputEvent
import com.pascal.registeronline.ui.theme.AppTheme

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
            Text("Data Utama")

            FormBasicComponent(
                title = AnnotatedString("Nama"),
                hintText = "Masukkan nama",
                value = uiState.name,
                onValueChange = event.setName,
                isError = uiState.nameError
            )

            FormBasicComponent(
                title = AnnotatedString("NIK"),
                hintText = "Masukkan NIK",
                value = uiState.nik,
                onValueChange = event.setNik,
                isError = uiState.nikError,
                isNumber = true
            )

            FormBasicComponent(
                title = AnnotatedString("Telepon"),
                hintText = "08xxx",
                value = uiState.phone,
                onValueChange = event.setPhone,
                isError = uiState.phoneError,
                isNumber = true
            )

            FormBasicComponent(
                title = AnnotatedString("Tempat Lahir"),
                hintText = "Jakarta",
                value = uiState.birthPlace,
                onValueChange = event.setBirthPlace,
                isError = false
            )

            FormBasicComponent(
                title = AnnotatedString("Tanggal Lahir"),
                hintText = "1990-01-01",
                value = uiState.birthDate,
                onValueChange = event.setBirthDate,
                isError = false
            )

            FormClickedComponent(
                title = AnnotatedString("Status"),
                hintText = "Pilih",
                value = uiState.status,
                onIconClick = event.openStatus
            )

            FormClickedComponent(
                title = AnnotatedString("Pekerjaan"),
                hintText = "Pilih",
                value = uiState.occupation,
                onIconClick = event.openPekerjaan
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
            Text("Alamat KTP")

            FormBasicComponent(
                title = AnnotatedString("Alamat"),
                hintText = "",
                value = uiState.address,
                onValueChange = event.setAddress,
                isError = false
            )

            FormBasicComponent(
                title = AnnotatedString("Provinsi"),
                hintText = "",
                value = uiState.provinsi,
                onValueChange = event.setProvinsi,
                isError = false
            )

            FormBasicComponent(
                title = AnnotatedString("Kota/Kabupaten"),
                hintText = "",
                value = uiState.kotaKabupaten,
                onValueChange = event.setKota,
                isError = false
            )

            FormBasicComponent(
                title = AnnotatedString("Kecamatan"),
                hintText = "",
                value = uiState.kecamatan,
                onValueChange = event.setKecamatan,
                isError = false
            )

            FormBasicComponent(
                title = AnnotatedString("Kelurahan"),
                hintText = "",
                value = uiState.kelurahan,
                onValueChange = event.setKelurahan,
                isError = false
            )

            FormBasicComponent(
                title = AnnotatedString("Kode Pos"),
                hintText = "",
                value = uiState.kodePos,
                onValueChange = event.setKodePos,
                isError = false,
                isNumber = true
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
            Text("Alamat Domisili")

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.isSameAddress,
                    onCheckedChange = event.setSameAddress
                )
                Text("Sama dengan KTP")
            }

            if (!uiState.isSameAddress) {
                FormBasicComponent(
                    title = AnnotatedString("Alamat Domisili"),
                    hintText = "",
                    value = uiState.alamatDomisili,
                    onValueChange = event.setDomisili,
                    isError = false
                )
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
            Text("Foto KTP")

            Row {
                ImagePicker(
                    path = uiState.ktpFile,
                    label = "Primary",
                    onClick = event.openCameraPrimary
                )

                Spacer(Modifier.width(8.dp))

                ImagePicker(
                    path = uiState.ktpFileSecondary,
                    label = "Secondary",
                    onClick = event.openCameraSecondary
                )
            }
        }

        item {
            Spacer(Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = event.saveDraft
            ) {
                Text("Simpan sebagai Draft")
            }
        }
    }
}

@Composable
fun RowScope.ImagePicker(
    path: String,
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(120.dp)
            .background(Color.LightGray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (path.isEmpty()) {
            Text(label)
        } else {
            AsyncImage(
                model = path,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
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