package com.test.todoapp

import android.app.Application
import com.test.todoapp.di.mLoginRepositoryModules
import com.test.todoapp.di.mNetworkModules
import com.test.todoapp.di.mUseCaseModules
import com.test.todoapp.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                mNetworkModules,
                mLoginRepositoryModules,
                mUseCaseModules,
                homeModule
            )

        }
    }
}