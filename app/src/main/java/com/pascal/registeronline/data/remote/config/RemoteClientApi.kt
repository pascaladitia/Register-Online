package com.pascal.registeronline.data.remote.config

import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.data.remote.dtos.login.LoginResponse
import com.pascal.registeronline.data.remote.dtos.member.MemberResponse
import com.pascal.registeronline.data.remote.dtos.profile.ProfileBody
import com.pascal.registeronline.data.remote.dtos.profile.ProfileResponse
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.data.remote.dtos.register.RegisterResponse
import com.pascal.registeronline.data.remote.dtos.sync.SyncDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Single

@Single
class RemoteClientApi(
    private val client: HttpClient
) {
    suspend fun register(body: RegisterBody): RegisterResponse {
        return client.post("register") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend fun login(body: LoginBody): LoginResponse {
        return client.post("login") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend fun syncData(content: MultiPartFormDataContent): SyncDataResponse =
        client.post("member") {
            contentType(ContentType.MultiPart.FormData)
            setBody(content)
        }.body()

    suspend fun getMember(): List<MemberResponse> {
        return client.get("member") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getProfile(body: ProfileBody): ProfileResponse {
        return client.get("profile") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }
}