package com.test.common.entities

data class CommonDataEntity(
    var dataDetailList: List<CommonItem> = emptyList(),
    var id: Int = 0,
)

data class CommonItem(
    var value: String,
    var color: Int = 0
)