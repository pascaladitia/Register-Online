package com.pascal.registeronline.data.remote.dtos.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
	val email: String? = null,
	val password: String? = null
)
