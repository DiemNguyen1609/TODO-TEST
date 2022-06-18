package com.test.domain.repositories

import com.test.domain.entities.CallResult
import io.reactivex.Flowable

interface TodoRepository {
    fun getCallList(): Flowable<CallResult>
}