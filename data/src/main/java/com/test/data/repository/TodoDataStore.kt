package com.test.data.repository

import com.test.domain.entities.CallResult
import io.reactivex.Flowable

interface TodoDataStore {
    fun getCallList(): Flowable<CallResult>
}