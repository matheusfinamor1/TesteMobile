package com.example.testemobile.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.testemobile.database.PurchaseDao
import com.example.testemobile.database.PurchaseDatabase
import com.example.testemobile.repository.car.CarRepositoryImpl
import com.example.testemobile.repository.postData.PostDataRepositoryImpl
import com.example.testemobile.repository.purchase.PurchaseRepositoryImpl
import com.example.testemobile.ui.viewmodel.HomeScreenViewModel
import com.example.testemobile.worker.PostWork
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {
    single<CarRepositoryImpl> { CarRepositoryImpl(get()) }
    single<PurchaseRepositoryImpl> { PurchaseRepositoryImpl(get()) }
    single { provideDatabase(get()) }
    single { provideDao(get()) }
    single { PostDataRepositoryImpl(get()) }
    single { WorkManager.getInstance(androidContext()) }
    viewModel { HomeScreenViewModel(get(), get(), get()) }
    worker { PostWork(androidContext(), get(), get()) }
}

val networkModule = module {
    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    contentType = ContentType.Application.Json
                )
            }
        }
    }
}

fun provideDatabase(application: Context): PurchaseDatabase =
    Room.databaseBuilder(
        application,
        PurchaseDatabase::class.java,
        table_db.TABLE_NAME
    ).fallbackToDestructiveMigration().build()

fun provideDao(database: PurchaseDatabase): PurchaseDao = database.purchaseDao()

object table_db {
    const val TABLE_NAME = "table_purchase_database"
}