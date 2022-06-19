package com.test.domain.repositories

import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import com.test.domain.entities.CallResult
import io.reactivex.Flowable

interface TodoRepository {
    fun getBuyList(): Flowable<BuyResult>
    fun getCallList(): Flowable<CallResult>
    fun getSellList(): Flowable<BuyResult>
    fun addSellItem(sellItem: BuyItemResult)
}