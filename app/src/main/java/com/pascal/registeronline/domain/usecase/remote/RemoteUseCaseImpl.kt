package com.pascal.registeronline.domain.usecase.remote

import com.pascal.registeronline.data.remote.dtos.login.LoginBody
import com.pascal.registeronline.data.remote.dtos.register.RegisterBody
import com.pascal.registeronline.data.repository.RemoteRepository
import com.pascal.registeronline.domain.mapper.toDomain
import com.pascal.registeronline.domain.model.Login
import com.pascal.registeronline.domain.model.Member
import com.pascal.registeronline.domain.model.Register
import com.pascal.registeronline.domain.model.SyncData
import io.ktor.client.request.forms.MultiPartFormDataContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class RemoteUseCaseImpl(
    private val repository: RemoteRepository
) : RemoteUseCase {
    override suspend fun register(body: RegisterBody): Flow<Register> = flow {
        emit(repository.register(body).toDomain())
    }

    override suspend fun login(body: LoginBody): Flow<Login> = flow {
        emit(repository.login(body).toDomain())
    }

    override suspend fun syncData(content: MultiPartFormDataContent): Flow<SyncData> = flow {
        emit(repository.syncData(content).toDomain())
    }

    override suspend fun getMember(): Flow<List<Member>> = flow {
        emit(repository.getMember().map { it.toDomain() })
    }
}
