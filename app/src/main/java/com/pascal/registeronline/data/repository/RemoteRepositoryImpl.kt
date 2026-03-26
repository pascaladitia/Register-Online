package com.pascal.registeronline.data.repository

import com.pascal.registeronline.data.remote.config.RemoteClientApi
import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.data.remote.dtos.login.LoginResponse
import com.pascal.registeronline.data.remote.dtos.member.MemberResponse
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.data.remote.dtos.register.RegisterResponse
import com.pascal.registeronline.data.remote.dtos.sync.SyncDataResponse
import io.ktor.client.request.forms.MultiPartFormDataContent
import org.koin.core.annotation.Single

@Single
class RemoteRepositoryImpl(
    private val api: RemoteClientApi
) : RemoteRepository {

    override suspend fun register(body: RegisterBody): RegisterResponse {
        return api.register(body)
    }

    override suspend fun login(body: LoginBody): LoginResponse {
        return api.login(body)
    }

    override suspend fun syncData(content: MultiPartFormDataContent): SyncDataResponse {
        return api.syncData(content)
    }

    override suspend fun getMember(): List<MemberResponse> {
        return api.getMember()
    }
}