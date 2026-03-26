package com.pascal.registeronline.ui.screen.input.state

data class InputUiState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val isSuccess: Boolean = false,

    // IDENTITAS
    val name: String = "",
    val nik: String = "",
    val phone: String = "",
    val birthPlace: String = "",
    val birthDate: String = "",
    val status: String = "",
    val occupation: String = "",

    // ERROR FIELD
    val nameError: Boolean = false,
    val nikError: Boolean = false,
    val phoneError: Boolean = false,

    // KTP
    val address: String = "",
    val provinsi: String = "",
    val kotaKabupaten: String = "",
    val kecamatan: String = "",
    val kelurahan: String = "",
    val kodePos: String = "",

    // DOMISILI
    val isSameAddress: Boolean = true,
    val alamatDomisili: String = "",

    // MEDIA
    val ktpFile: String = "",
    val ktpFileSecondary: String = "",

    // DROPDOWN
    val statusList: List<String> = listOf("Lajang", "Menikah"),
    val pekerjaanList: List<String> = listOf("Pegawai Swasta", "Wiraswasta"),
    val isStatusSheet: Pair<Boolean, Int> = false to -1,
    val isPekerjaanSheet: Pair<Boolean, Int> = false to -1,

    // CAMERA
    val openCameraPrimary: Boolean = false,
    val openCameraSecondary: Boolean = false
)