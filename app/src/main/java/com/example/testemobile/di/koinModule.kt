package com.example.testemobile.di

import com.example.testemobile.repository.CarRepositoryImpl
import com.example.testemobile.ui.viewmodel.HomeScreenViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CarRepositoryImpl> {CarRepositoryImpl(get())}
    viewModel{ HomeScreenViewModel(get()) }
}

val networkModule = module {
    single {
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(ContentNegotiation){
                json(
                   contentType = ContentType.Application.Json
                )
            }
        }
    }
}