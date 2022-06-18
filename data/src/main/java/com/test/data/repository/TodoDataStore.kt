package com.test.data.repository

import com.test.domain.entities.BuyResult
import io.reactivex.Flowable

interface TodoDataStore {
    fun getCallList(): Flowable<BuyResult>
}