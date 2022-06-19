package com.test.domain.entities

import java.math.BigDecimal

data class CallResult(
    var callResults: List<CallItemResult> = emptyList()
)

data class CallItemResult(
    var id: Int = 0,
    var number: String = "",
    var name: String = "",
)