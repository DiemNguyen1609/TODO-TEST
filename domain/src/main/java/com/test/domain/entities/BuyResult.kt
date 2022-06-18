package com.test.domain.entities

import java.math.BigDecimal

data class BuyResult(
    var callResults: List<BuyItemResult> = emptyList()
)

data class BuyItemResult(
    var id: Float = 0f,
    var type: Float = 0f,
    var quantity: Float = 0f,
    var name: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)