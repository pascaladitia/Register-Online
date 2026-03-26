package com.pascal.registeronline.domain.mapper

import com.pascal.registeronline.data.remote.dtos.sync.SyncDataResponse
import com.pascal.registeronline.domain.model.Member
import com.pascal.registeronline.domain.model.SyncData

fun SyncDataResponse.toDomain(): SyncData {
    return SyncData(
        message = message.orEmpty(),
        member = member?.toDomain() ?: Member(
            id = 0,
            name = "",
            nik = "",
            phone = "",
            ktpUrl = "",
            ktpUrlSecondary = ""
        )
    )
}