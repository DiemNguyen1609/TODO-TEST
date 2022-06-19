package com.test.domain.usecases

import com.test.domain.entities.BuyItemResult
import com.test.domain.repositories.TodoRepository

class AddSellUseCase(
    private val repositories: TodoRepository
) {
    fun addSellList(data: BuyItemResult){
        return repositories.addSellItem(data)
    }

}