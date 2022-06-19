package com.test.todoapp.di

import com.test.data.api.TodoApi
import com.test.data.local.db.createSellDao
import com.test.todoapp.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit

val mDatabaseModules = module {
    single { createSellDao(get()) }
}