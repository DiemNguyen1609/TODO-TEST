package com.test.data.entities

import java.math.BigDecimal

data class CallEntity(
    var callList: List<CallItemEntity>? = null,
) {
    companion object {
        fun create(map: Map<String, Any?>): CallEntity {
            val model = CallEntity()
            map.forEach { it ->
                val dataResult = it.value as List<Map<String, Any?>>?
                val dataListEntity
                        : List<CallItemEntity>
                ? = dataResult?.map {
                    CallItemEntity.create(
                        it
                    )
                }
                model.callList = dataListEntity
            }
            return model
        }
    }
}

data class CallItemEntity(
    var name: String? = null,
    var id: Int? = null,
    var type: Int? = null,
    var quantity: Int? = null,
    var price: BigDecimal? = null
) {
    companion object {
        fun create(map: Map<String, Any?>): CallItemEntity {
            val model = CallItemEntity()
            map.forEach {
                val value = it.value
                when (it.key) {
                    "name" -> model.name = value as String
                    "id" -> model.id = value as Int
                    "type" -> model.type = value as Int
                    "quantity" -> model.quantity = value as Int
                    "price" -> model.price = value as BigDecimal
                }
            }
            return model
        }
    }
}