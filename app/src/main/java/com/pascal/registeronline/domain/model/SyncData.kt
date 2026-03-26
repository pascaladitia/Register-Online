package com.pascal.registeronline.domain.model

import java.io.Serializable

data class SyncData(
    val message: String,
    val member: Member
) : Serializable

data class Member(
    val id: Int,
    val name: String,
    val nik: String,
    val phone: String,
    val ktpUrl: String,
    val ktpUrlSecondary: String
) : Serializable