package com.test.data.repository

import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class TodoRepositoryImpl(private val remote: TodoRemoteImpl) : TodoRepository {
    override fun getCallList(): Flowable<BuyResult> {
        return remote.getBuyList()
    }

    override fun getSellList(): Flowable<BuyResult> {
        return remote.getSellList()
    }

    override fun addSellItem(sellItem: BuyItemResult) {
        return remote.addSellItem(sellItem)
    }

}