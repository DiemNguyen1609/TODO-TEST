package com.test.todoapp.home.di

import com.test.todoapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(context = get(), callUseCase = get(), addSellUseCase = get(), buyUseCase = get(), getSellUseCase = get()) }
}