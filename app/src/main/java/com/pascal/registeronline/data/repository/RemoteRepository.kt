package com.pascal.registeronline.data.repository

import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.data.remote.dtos.login.LoginResponse
import com.pascal.registeronline.data.remote.dtos.member.MemberResponse
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.data.remote.dtos.register.RegisterResponse
import com.pascal.registeronline.data.remote.dtos.sync.SyncDataResponse
import io.ktor.client.request.forms.MultiPartFormDataContent

interface RemoteRepository {
    suspend fun register(body: RegisterBody) : RegisterResponse
    suspend fun login(body: LoginBody) : LoginResponse
    suspend fun syncData(content: MultiPartFormDataContent) : SyncDataResponse
    suspend fun getMember() : List<MemberResponse>
}