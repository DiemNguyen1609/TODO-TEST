package com.test.todoapp.di

import com.test.domain.usecases.CallUseCase
import com.test.todoapp.common.AsyncFlowableTransformer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mUseCaseModules = module {
    factory(qualifier = named("call_usecase")) {
        CallUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}