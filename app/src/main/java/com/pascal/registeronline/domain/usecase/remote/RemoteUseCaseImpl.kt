package com.pascal.registeronline.domain.usecase.remote

import com.pascal.registeronline.data.repository.NewsRepository
import com.pascal.registeronline.domain.mapper.toDomain
import com.pascal.registeronline.domain.model.Dashboard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class RemoteUseCaseImpl(
    private val repository: NewsRepository
) : RemoteUseCase {

    override suspend fun dashboard(): Flow<Dashboard> = flow {
        emit(repository.dashboard().toDomain())
    }
}
