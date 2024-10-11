package com.example.kilohealth.app

import android.app.Application
import com.example.kilohealth.module.KiloHealthModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(KiloHealthModule().module))
        }
    }
}