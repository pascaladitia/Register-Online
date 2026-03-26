package com.pascal.registeronline.domain.usecase.remote

import com.pascal.registeronline.domain.model.Dashboard
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    suspend fun dashboard(): Flow<Dashboard>
}
