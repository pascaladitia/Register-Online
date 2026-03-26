package com.pascal.registeronline.data.remote.dtos.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
	val code: String? = null,
	val desc: String? = null,
	val error: String? = null
)
