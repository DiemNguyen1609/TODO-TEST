package com.test.domain.usecases

import com.test.domain.common.BaseFlowableUseCase
import com.test.domain.common.FlowableRxTransformer
import com.test.domain.entities.BuyResult
import com.test.domain.repositories.TodoRepository
import io.reactivex.Flowable

class GetBuyUseCase(
    transformer: FlowableRxTransformer<BuyResult>,
    private val repositories: TodoRepository
): BaseFlowableUseCase<BuyResult>(transformer) {

    override fun createFlowable(data: Map<String, Any>?): Flowable<BuyResult> {
        return repositories.getBuyList()
    }

    fun requestBuyList(): Flowable<BuyResult> {
        return single()
    }
}