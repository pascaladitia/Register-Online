package com.pascal.registeronline.data.remote.dtos.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(

	@SerialName("id")
	val id: Int? = null,

	@SerialName("full_name")
	val fullName: Boolean? = null,

	@SerialName("email")
	val email: String? = null
)