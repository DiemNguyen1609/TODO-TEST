package com.test.todoapp.di

import com.test.data.api.TodoApi
import com.test.data.repository.ToDoRepositoryImpl
import com.test.data.repository.TodoRemoteImpl
import com.test.domain.repositories.TodoRepository
import com.test.todoapp.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"

val mNetworkModules = module {
    single { createNetworkClient(BuildConfig.BASE_URL) }
    single { (get() as Retrofit).create(TodoApi::class.java) }
}

val mLoginRepositoryModules = module {
    single {
        TodoRemoteImpl(
            api = get()
        )
    }
    single { ToDoRepositoryImpl(remote = get()) as TodoRepository }
}