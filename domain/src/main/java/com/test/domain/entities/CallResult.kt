package com.test.domain.entities

import java.math.BigDecimal

data class CallResult(
    var callResults: List<CallItemResult> = emptyList()
)

data class CallItemResult(
    var id: Int = 0,
    var type: Int = 0,
    var quantity: Int = 0,
    var name: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)