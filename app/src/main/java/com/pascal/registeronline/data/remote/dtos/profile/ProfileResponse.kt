package com.pascal.registeronline.data.remote.dtos.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(

	@SerialName("id")
	val id: String? = null,

	@SerialName("full_name")
	val fullName: String? = null,

	@SerialName("email")
	val email: String? = null
)