package com.pascal.registeronline.domain.mapper

import com.pascal.registeronline.data.remote.dtos.member.MemberResponse
import com.pascal.registeronline.domain.model.Member

fun MemberResponse.toDomain(): Member {
    return Member(
        id = id ?: 0,
        name = name.orEmpty(),
        nik = nik.orEmpty(),
        phone = phone.orEmpty(),
        ktpUrl = ktpUrl.orEmpty(),
        ktpUrlSecondary = ktpUrlSecondary.orEmpty()
    )
}