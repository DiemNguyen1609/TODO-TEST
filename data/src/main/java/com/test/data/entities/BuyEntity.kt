package com.test.data.entities

import com.test.domain.entities.BuyItemResult
import java.math.BigDecimal

data class BuyEntity(
    var callList: List<BuyItemEntity>? = null,
) {
    companion object {
        fun create(mapResult: List<BuyItemResult>): BuyEntity {
            val model = BuyEntity()
            var dataResult : MutableList<BuyItemEntity> = mutableListOf()
            mapResult?.forEach {
                dataResult.add(BuyItemEntity.create(it))
            }
            model.callList = dataResult
            return model
        }
    }
}

data class BuyItemEntity(
    var name: String? = null,
    var id: Int? = null,
    var type: Int? = null,
    var quantity: Int? = null,
    var price: BigDecimal? = null
) {
    companion object {
        fun create(map: BuyItemResult): BuyItemEntity {
            val model = BuyItemEntity()

                model.name = map?.name ?: ""
                model.id = map?.id ?: 0
                model.type = map?.type ?: 0
                model.quantity = map?.quantity ?: 0
                model.price = map?.price ?: BigDecimal.ZERO

            return model
        }
    }
}