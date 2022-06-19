package com.test.data.repository

import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import io.reactivex.Flowable

interface TodoDataStore { // Interface LocalDatabase, RemoteData
    fun getBuyList(): Flowable<BuyResult>
    fun getSellList(): Flowable<BuyResult>
    fun addSellItem(sellItem: BuyItemResult)
}