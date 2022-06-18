package com.test.domain.entities

import java.math.BigDecimal

data class CallResult(
    var callResults: List<CallItemEntity> = emptyList()
)

data class CallItemEntity(
    var id: Int = 0,
    var type: Int = 0,
    var quantity: Int = 0,
    var name: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)