package com.test.data.repository

import com.test.domain.entities.BuyResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class ToDoRepositoryImpl(private val remote: TodoRemoteImpl) : TodoRepository {
    override fun getCallList(): Flowable<BuyResult> {
        return remote.getCallList()
    }

}