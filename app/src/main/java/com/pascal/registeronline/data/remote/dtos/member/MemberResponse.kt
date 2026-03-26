package com.pascal.registeronline.data.remote.dtos.member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberResponse(
	@SerialName("id")
	val id: Int? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("nik")
	val nik: String? = null,

	@SerialName("phone")
	val phone: String? = null,

	@SerialName("ktp_url")
	val ktpUrl: String? = null,

	@SerialName("ktp_url_secondary")
	val ktpUrlSecondary: String? = null
)