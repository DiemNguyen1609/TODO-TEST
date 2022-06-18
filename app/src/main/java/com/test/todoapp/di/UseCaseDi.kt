package com.test.todoapp.di

import com.test.domain.usecases.CallUseCase
import com.test.todoapp.common.AsyncFlowableTransformer
import org.koin.dsl.module

val mUseCaseModules = module {
    factory {
        CallUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}