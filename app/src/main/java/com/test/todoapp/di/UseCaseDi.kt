package com.test.todoapp.di

import com.test.domain.usecases.AddSellUseCase
import com.test.domain.usecases.BuyUseCase
import com.test.todoapp.common.AsyncFlowableTransformer
import org.koin.dsl.module

val mUseCaseModules = module {
    factory {
        BuyUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
    factory {
        AddSellUseCase(
            repositories = get()
        )
    }
}