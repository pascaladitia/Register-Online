package com.pascal.registeronline.data.remote.dtos.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(

	@SerialName("message")
	val message: String? = null

)