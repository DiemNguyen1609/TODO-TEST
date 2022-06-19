package com.test.domain.entities

import java.math.BigDecimal

data class BuyResult(
    var callResults: List<BuyItemResult> = emptyList()
)

data class BuyItemResult(
    var id: Int =0,
    var type: Int = 0,
    var quantity: Int = 0,
    var name: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)