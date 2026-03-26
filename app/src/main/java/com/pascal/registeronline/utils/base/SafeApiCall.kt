package com.pascal.registeronline.utils.base

import com.pascal.registeronline.data.remote.dtos.base.BaseResponse
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

abstract class SafeApiCall {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    inline fun <reified T> safeApiCall(
        crossinline apiCall: suspend () -> T
    ): Flow<T> = flow {
        emit(apiCall.invoke())
    }.catch { e ->

        val message = when (e) {
            is ClientRequestException -> {
                val errorBody = e.response.bodyAsText()
                parseError(errorBody)
            }
            is ServerResponseException -> {
                val errorBody = e.response.bodyAsText()
                parseError(errorBody)
            }
            is IOException -> {
                "Network error. Please check your connection."
            }
            else -> {
                e.message ?: "Unknown error"
            }
        }

        throw Exception(message)
    }

    fun parseError(errorBody: String?): String {
        return try {
            errorBody?.let {
                json.decodeFromString<BaseResponse>(it).error
            } ?: "Unknown error"
        } catch (_: Exception) {
            errorBody ?: "Unknown error"
        } ?: "Unknown error"
    }
}