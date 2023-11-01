package com.sergeevme.cleanandroid.app

import android.app.Application
import com.sergeevme.cleanandroid.di.appModule
import com.sergeevme.cleanandroid.di.dataModule
import com.sergeevme.cleanandroid.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/*
* Koin is a pragmatic and lightweight dependency injection framework for Kotlin developers.
* Koin is a DSL, a light container and a pragmatic API.
*/
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }

}