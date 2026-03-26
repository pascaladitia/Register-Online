package com.pascal.registeronline.domain.mapper

import com.pascal.registeronline.data.remote.dtos.dashboard.DashboardResponse
import com.pascal.registeronline.domain.model.Dashboard

fun DashboardResponse.toDomain(): Dashboard {
    return Dashboard(
        code = this.code ?: 0,
        success = this.success ?: false,
        message = this.message.orEmpty()
    )
}