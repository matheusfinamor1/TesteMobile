package com.example.testemobile

import android.app.Application
import com.example.testemobile.di.appModule
import com.example.testemobile.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            workManagerFactory()
            modules(appModule, networkModule)
        }
    }
}