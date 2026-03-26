package com.pascal.registeronline.data.remote.dtos.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
	val email: String? = null,
	val password: String? = null,
	val full_name: String? = null,
	val phone: String? = null
)
