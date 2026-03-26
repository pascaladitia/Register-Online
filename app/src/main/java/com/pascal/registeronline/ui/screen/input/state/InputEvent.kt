package com.pascal.registeronline.ui.screen.input.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalInputEvent = compositionLocalOf { InputEvent() }

@Stable
data class InputEvent(

    val onBack: () -> Unit = {},

    val setName: (String) -> Unit = {},
    val setNik: (String) -> Unit = {},
    val setPhone: (String) -> Unit = {},
    val setBirthPlace: (String) -> Unit = {},
    val setBirthDate: (String) -> Unit = {},

    val setAddress: (String) -> Unit = {},
    val setProvinsi: (String) -> Unit = {},
    val setKota: (String) -> Unit = {},
    val setKecamatan: (String) -> Unit = {},
    val setKelurahan: (String) -> Unit = {},
    val setKodePos: (String) -> Unit = {},

    val setSameAddress: (Boolean) -> Unit = {},
    val setDomisili: (String) -> Unit = {},

    val openStatus: () -> Unit = {},
    val dismissStatus: () -> Unit = {},
    val selectStatus: (Int, String) -> Unit = { _, _ -> },

    val openPekerjaan: () -> Unit = {},
    val dismissPekerjaan: () -> Unit = {},
    val selectPekerjaan: (Int, String) -> Unit = { _, _ -> },

    val openCameraPrimary: () -> Unit = {},
    val openCameraSecondary: () -> Unit = {},

    val saveDraft: () -> Unit = {}
)