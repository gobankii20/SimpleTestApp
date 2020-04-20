package com.totaloil.simpletest

import android.app.Application
import com.totaloil.simpletest.di.module.networkModule
import com.totaloil.simpletest.di.module.repositoryModule
import com.totaloil.simpletest.di.module.utilityModule
import com.totaloil.simpletest.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(arrayListOf(networkModule,utilityModule,repositoryModule, viewModelModule))
            androidLogger()
        }
    }
}