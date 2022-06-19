package com.test.todoapp.di

import com.test.domain.usecases.AddSellUseCase
import com.test.domain.usecases.GetBuyUseCase
import com.test.domain.usecases.GetCallUseCase
import com.test.todoapp.common.AsyncFlowableTransformer
import org.koin.dsl.module

val mUseCaseModules = module {
    factory {
        GetBuyUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
    factory {
        AddSellUseCase(
            repositories = get()
        )
    }
    factory {
        GetCallUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}