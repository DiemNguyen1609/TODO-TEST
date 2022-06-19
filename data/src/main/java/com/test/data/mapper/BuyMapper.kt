package com.test.data.mapper

import com.test.data.entities.BuyEntity
import com.test.data.entities.BuyItemEntity
import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import java.math.BigDecimal

class BuyMapper {

    fun mapToModel(callEntity: BuyEntity): BuyResult {
        return BuyResult().apply {
            callEntity.let {
                callResults = it?.callList?.map {
                    mapToCallResult(it)
                } ?: emptyList()
            }
        }
    }

    private fun mapToCallResult(callItemEntity: BuyItemEntity): BuyItemResult {
        return BuyItemResult().apply {
            callItemEntity?.let {
                id = it.id ?: 0
                name = it.name ?: ""
                type = it.type ?: 0
                quantity = it.quantity ?: 0
                price = it.price ?: BigDecimal.ZERO
            }
        }
    }

}