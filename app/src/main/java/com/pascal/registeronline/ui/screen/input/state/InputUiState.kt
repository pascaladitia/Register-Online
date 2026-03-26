package com.pascal.registeronline.ui.screen.input.state

data class InputUiState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val isSuccess: Boolean = false,

    val name: String = "",
    val nik: String = "",
    val phone: String = "",
    val birthPlace: String = "",
    val birthDate: String = "",
    val status: String = "",
    val gender: String = "",
    val occupation: String = "",

    val nameError: Boolean = false,
    val nikError: Boolean = false,
    val phoneError: Boolean = false,

    val address: String = "",
    val provinsi: String = "",
    val kotaKabupaten: String = "",
    val kecamatan: String = "",
    val kelurahan: String = "",
    val kodePos: String = "",

    val isSameAddress: Boolean = true,
    val alamatDomisili: String = "",

    val ktpFile: String = "",
    val ktpFileSecondary: String = "",

    val genderList: List<String> = listOf("Laki-laki", "Perempuan"),
    val statusList: List<String> = listOf("Lajang", "Menikah"),
    val pekerjaanList: List<String> = listOf("Pegawai Swasta", "Wiraswasta"),

    val isGenderSheet: Pair<Boolean, Int> = false to -1,
    val isStatusSheet: Pair<Boolean, Int> = false to -1,
    val isPekerjaanSheet: Pair<Boolean, Int> = false to -1,

    val openBirthDate: Boolean = false,
    val openCameraPrimary: Boolean = false,
    val openCameraSecondary: Boolean = false,

    val previewImage: String = "",
    val isPreview: Boolean = false,
    val isPrimaryCamera: Boolean = true
)