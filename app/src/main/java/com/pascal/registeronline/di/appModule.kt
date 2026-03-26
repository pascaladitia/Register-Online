package com.pascal.registeronline.di

import androidx.room.Room
import com.pascal.registeronline.data.local.database.AppDatabase
import com.pascal.registeronline.data.local.database.DatabaseConstants
import com.pascal.registeronline.data.local.repository.LocalRepository
import com.pascal.registeronline.data.local.repository.LocalRepositoryImpl
import com.pascal.registeronline.data.remote.config.KtorClientFactory
import com.pascal.registeronline.data.remote.config.RemoteClientApi
import com.pascal.registeronline.data.repository.RemoteRepository
import com.pascal.registeronline.data.repository.RemoteRepositoryImpl
import com.pascal.registeronline.domain.usecase.local.LocalUseCase
import com.pascal.registeronline.domain.usecase.local.LocalUseCaseImpl
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCase
import com.pascal.registeronline.domain.usecase.remote.RemoteUseCaseImpl
import com.pascal.registeronline.ui.screen.home.HomeViewModel
import com.pascal.registeronline.ui.screen.login.LoginViewModel
import com.pascal.registeronline.ui.screen.profile.ProfileViewModel
import com.pascal.registeronline.ui.screen.register.RegisterViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DatabaseConstants.DB_NAME
        )
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    // Remote
    single<HttpClient> {
        KtorClientFactory.create(androidContext())
    }

    single<RemoteClientApi> {
        RemoteClientApi(get())
    }

    // Data source
    singleOf(::LocalRepositoryImpl) { bind<LocalRepository>() }

    // Repository
    singleOf(::RemoteRepositoryImpl) { bind<RemoteRepository>() }

    // UseCases
    singleOf(::LocalUseCaseImpl) { bind<LocalUseCase>() }
    singleOf(::RemoteUseCaseImpl) { bind<RemoteUseCase>() }

    // ViewModels
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProfileViewModel)
}
