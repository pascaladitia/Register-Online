package com.pascal.registeronline.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "draft")
data class DraftEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val nik: String,
    val phone: String,
    val birthPlace: String,
    val birthDate: String,
    val status: String,
    val occupation: String,

    val address: String,
    val provinsi: String,
    val kotaKabupaten: String,
    val kecamatan: String,
    val kelurahan: String,
    val kodePos: String,

    val alamatDomisili: String,
    val provinsiDomisili: String,
    val kotaKabupatenDomisili: String,
    val kecamatanDomisili: String,
    val kelurahanDomisili: String,
    val kodePosDomisili: String,

    val ktpFile: String,
    val ktpFileSecondary: String
)