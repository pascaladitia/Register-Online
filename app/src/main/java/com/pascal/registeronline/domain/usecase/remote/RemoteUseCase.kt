package com.pascal.registeronline.domain.usecase.remote

import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.data.remote.dtos.profile.ProfileBody
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.domain.model.Login
import com.pascal.registeronline.domain.model.Member
import com.pascal.registeronline.domain.model.Profile
import com.pascal.registeronline.domain.model.Register
import com.pascal.registeronline.domain.model.SyncData
import io.ktor.client.request.forms.MultiPartFormDataContent
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    suspend fun register(body: RegisterBody) : Flow<Register>
    suspend fun login(body: LoginBody): Flow<Login>
    suspend fun syncData(content: MultiPartFormDataContent) : Flow<SyncData>
    suspend fun getMember() : Flow<List<Member>>
    suspend fun profile(body: ProfileBody) : Flow<Profile>
}
