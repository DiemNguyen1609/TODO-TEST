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
    single(qualifier = named(RETROFIT_INSTANCE)) { createNetworkClient(BuildConfig.BASE_URL) }
    single(qualifier = named(API)) { (get() as Retrofit).create(TodoApi::class.java) }
}

val mLoginRepositoryModules = module {
    single(qualifier = named("todo_remote")) {
        TodoRemoteImpl(
            api = get(qualifier = named(API))
        )
    }
    single { ToDoRepositoryImpl(remote = get(qualifier = named("movie_remote"))) as TodoRepository }
}