package com.test.data.repository

import com.test.data.api.TodoApi
import com.test.domain.entities.CallResult
import io.reactivex.Flowable

class TodoRemoteImpl constructor(private val api: TodoApi) : TodoDataStore {
    override fun getCallList(): Flowable<CallResult> {


    }
}