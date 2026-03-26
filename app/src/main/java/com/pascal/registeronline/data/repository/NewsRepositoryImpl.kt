package com.pascal.registeronline.data.repository

import com.pascal.registeronline.data.remote.api.KtorClientApi
import com.pascal.registeronline.data.remote.dtos.dashboard.DashboardResponse
import org.koin.core.annotation.Single

@Single
class NewsRepositoryImpl(
    private val api: KtorClientApi
) : NewsRepository {

    override suspend fun dashboard(): DashboardResponse {
        return api.dashboard()
    }
}