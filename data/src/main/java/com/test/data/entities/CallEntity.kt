package com.test.data.entities

import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.CallItemResult
import java.math.BigDecimal


data class CallEntity(
    var callList: List<CallItemEntity>? = null,
) {
    companion object {
        fun create(mapResult: List<CallItemResult>): CallEntity {
            val model = CallEntity()
            var dataResult: MutableList<CallItemEntity> = mutableListOf()
            mapResult.forEach {
                dataResult.add(CallItemEntity.create(it))
            }
            model.callList = dataResult
            return model
        }
    }
}


data class CallItemEntity(
    var name: String? = null,
    var id: Int? = null,
    var number: String? = null,
) {
    companion object {
        fun create(map: CallItemResult): CallItemEntity {
            val model = CallItemEntity()

            model.name = map.name ?: ""
            model.id = map.id ?: 0
            model.number = map.number ?: ""

            return model
        }
    }
}