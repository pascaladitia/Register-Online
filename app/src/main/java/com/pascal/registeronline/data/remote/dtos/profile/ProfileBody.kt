package com.pascal.registeronline.data.remote.dtos.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileBody(
	val email: String? = null,
	val password: String? = null
)
