package com.pascal.registeronline.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
	val token: String
)