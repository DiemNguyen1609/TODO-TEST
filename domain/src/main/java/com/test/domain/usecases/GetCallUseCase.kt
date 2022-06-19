package com.test.domain.usecases

import com.test.domain.common.BaseFlowableUseCase
import com.test.domain.common.FlowableRxTransformer
import com.test.domain.entities.CallResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class GetCallUseCase(
    transformer: FlowableRxTransformer<CallResult>,
    private val repositories: TodoRepository
): BaseFlowableUseCase<CallResult>(transformer) {
    override fun createFlowable(data: Map<String, Any>?): Flowable<CallResult> {
        return repositories.getCallList()
    }

    fun requestCallList(): Flowable<CallResult> {
        return single()
    }
}