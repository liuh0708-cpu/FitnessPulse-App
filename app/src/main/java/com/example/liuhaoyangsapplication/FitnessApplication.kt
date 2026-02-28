package com.example.liuhaoyangsapplication

import android.app.Application
import com.example.liuhaoyangsapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FitnessApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FitnessApplication)
            modules(appModule)
        }
    }
}