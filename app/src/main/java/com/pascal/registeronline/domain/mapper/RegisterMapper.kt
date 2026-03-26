package com.pascal.registeronline.domain.mapper

import com.pascal.registeronline.data.remote.dtos.register.RegisterResponse
import com.pascal.registeronline.domain.model.Register

fun RegisterResponse.toDomain(): Register {
    return Register(
        message = this.message ?: ""
    )
}