package com.test.domain.repositories

import com.test.domain.entities.BuyResult
import io.reactivex.Flowable

interface TodoRepository {
    fun getCallList(): Flowable<BuyResult>
}