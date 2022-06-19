package com.test.data.local.db

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromLong(value: Long?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun toLong(data: BigDecimal?): Long? {
        return data?.toLong()
    }
}