package com.pascal.registeronline.domain.usecase.remote

import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.data.remote.dtos.profile.ProfileBody
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.data.repository.RemoteRepository
import com.pascal.registeronline.domain.mapper.toDomain
import com.pascal.registeronline.domain.model.Login
import com.pascal.registeronline.domain.model.Member
import com.pascal.registeronline.domain.model.Profile
import com.pascal.registeronline.domain.model.Register
import com.pascal.registeronline.domain.model.SyncData
import com.pascal.registeronline.utils.base.SafeApiCall
import io.ktor.client.request.forms.MultiPartFormDataContent
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class RemoteUseCaseImpl(
    private val repository: RemoteRepository
) : RemoteUseCase, SafeApiCall() {

    override suspend fun register(body: RegisterBody): Flow<Register> =
        safeApiCall { repository.register(body).toDomain() }

    override suspend fun login(body: LoginBody): Flow<Login> =
        safeApiCall { repository.login(body).toDomain() }

    override suspend fun syncData(content: MultiPartFormDataContent): Flow<SyncData> =
        safeApiCall { repository.syncData(content).toDomain() }

    override suspend fun getMember(): Flow<List<Member>> =
        safeApiCall { repository.getMember().map { it.toDomain() } }

    override suspend fun profile(body: ProfileBody): Flow<Profile> =
        safeApiCall { repository.getProfile(body).toDomain() }
}
