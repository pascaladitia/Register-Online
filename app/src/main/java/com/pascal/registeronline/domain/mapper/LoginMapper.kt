package com.pascal.registeronline.domain.mapper

import com.pascal.registeronline.data.remote.dtos.login.LoginResponse
import com.pascal.registeronline.domain.model.Login

fun LoginResponse.toDomain(): Login {
    return Login(
        token = this.token ?: ""
    )
}