package com.test.domain.usecases

import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class GetSellUseCase(
    private val repositories: TodoRepository
) {
    fun getSellList(): Flowable<BuyResult> {
        return repositories.getSellList()
    }

}