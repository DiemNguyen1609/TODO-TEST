package com.test.data.mapper

import com.test.data.entities.CallEntity
import com.test.data.entities.CallItemEntity
import com.test.domain.entities.CallItemResult
import com.test.domain.entities.CallResult
import java.math.BigDecimal

class CallMapper {

    fun mapToModel(callEntity: CallEntity): CallResult {
        return CallResult().apply {
            callEntity.let {
                callResults = it?.callList?.map {
                    mapToCallResult(it)
                } ?: emptyList()
            }
        }
    }

    private fun mapToCallResult(callItemEntity: CallItemEntity): CallItemResult {
        return CallItemResult().apply {
            callItemEntity.let {
                id = it?.id ?: 0
                name = it?.name ?: ""
                type = it?.type ?: 0
                quantity = it?.quantity ?: 0
                price = it?.price ?: BigDecimal.ZERO
            }
        }
    }

}