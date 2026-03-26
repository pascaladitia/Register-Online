package com.pascal.registeronline.data.repository

import com.pascal.registeronline.data.remote.dtos.dashboard.DashboardResponse

interface NewsRepository {
    suspend fun dashboard() : DashboardResponse
}