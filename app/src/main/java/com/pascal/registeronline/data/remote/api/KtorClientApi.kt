package com.pascal.registeronline.data.remote.api

import android.content.Context
import com.pascal.registeronline.data.remote.client
import com.pascal.registeronline.data.remote.dtos.dashboard.DashboardResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single
class KtorClientApi(private val context: Context) {
    suspend fun dashboard(): DashboardResponse {
        return client.get("http:///dashboard").body()
    }
}