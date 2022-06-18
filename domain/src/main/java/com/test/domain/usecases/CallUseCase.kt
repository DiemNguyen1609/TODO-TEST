package com.test.domain.usecases

import com.test.domain.common.BaseFlowableUseCase
import com.test.domain.common.FlowableRxTransformer
import com.test.domain.entities.CallResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class CallUseCase(
    transformer: FlowableRxTransformer<CallResult>,
    private val repositories: TodoRepository
): BaseFlowableUseCase<CallResult>(transformer) {
    override fun createFlowable(data: Map<String, Any>?): Flowable<CallResult> {
        val map = mutableMapOf<String, String>()
        return if (data !== null) {
            data.forEach { map[it.key] = it.value.toString() }
            repositories.getCallList()
        } else
            repositories.getCallList()
    }

    fun requestDiscover(): Flowable<CallResult> {
        return single()
    }
}