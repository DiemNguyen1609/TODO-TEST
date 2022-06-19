package com.test.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.data.entities.SellEntity
import com.test.domain.entities.BuyResult
import io.reactivex.Flowable

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSellData(sellItem: SellEntity)

    @Query("SELECT * FROM ItemToSell")
    fun getSellList(): Flowable<List<SellEntity>>
}