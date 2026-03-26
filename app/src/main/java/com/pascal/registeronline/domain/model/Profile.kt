package com.pascal.registeronline.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
	val id: String,
	val fullName: String,
	val email: String
)