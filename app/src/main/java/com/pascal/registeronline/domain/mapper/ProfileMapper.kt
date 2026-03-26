package com.pascal.registeronline.domain.mapper

import com.pascal.registeronline.data.remote.dtos.profile.ProfileResponse
import com.pascal.registeronline.domain.model.Profile

fun ProfileResponse.toDomain(): Profile {
    return Profile(
        id = id.orEmpty(),
        fullName = fullName.orEmpty(),
        email = email.orEmpty()
    )
}