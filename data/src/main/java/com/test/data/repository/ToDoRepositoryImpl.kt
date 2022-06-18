package com.test.data.repository

import com.test.domain.entities.CallResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class ToDoRepositoryImpl(private val remote: TodoRemoteImpl) : TodoRepository {
    override fun getCallList(): Flowable<CallResult> {
        return remote.getCallList()
    }

}