package com.pascal.registeronline.data.remote.dtos.sync

import com.pascal.registeronline.data.remote.dtos.member.MemberResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SyncDataResponse(
	@SerialName("message")
	val message: String? = null,

	@SerialName("member")
	val member: MemberResponse? = null
)