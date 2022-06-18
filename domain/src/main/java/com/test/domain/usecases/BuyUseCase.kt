package com.test.domain.usecases

import com.test.domain.common.BaseFlowableUseCase
import com.test.domain.common.FlowableRxTransformer
import com.test.domain.entities.BuyResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class BuyUseCase(
    transformer: FlowableRxTransformer<BuyResult>,
    private val repositories: TodoRepository
): BaseFlowableUseCase<BuyResult>(transformer) {
    override fun createFlowable(data: Map<String, Any>?): Flowable<BuyResult> {
        val map = mutableMapOf<String, String>()
        return if (data !== null) {
            data.forEach { map[it.key] = it.value.toString() }
            repositories.getCallList()
        } else
            repositories.getCallList()
    }

    fun requestDiscover(): Flowable<BuyResult> {
        return single()
    }
}