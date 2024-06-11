package com.app.kotlincomposecleanarchitcture

import android.app.Application
import com.app.kotlincomposecleanarchitcture.di.allModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(allModule)
        }
    }
}