package com.pascal.registeronline.data.remote.config

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.pascal.registeronline.BuildConfig
import com.pascal.registeronline.data.prefs.PreferencesLogin
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

object KtorClientFactory {

    fun create(context: Context): HttpClient {

        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
            )
            .alwaysReadResponseBody(true)
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()

        return HttpClient(OkHttp) {

            expectSuccess = true

            engine {
                preconfigured = okHttpClient
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }

            defaultRequest {
                url(BuildConfig.BASE_URL)

                val token = PreferencesLogin.getAccessToken(context)
                if (token.isNotEmpty()) {
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
            }

            install(HttpTimeout) {
                val timeout = 60_000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }
    }
}
