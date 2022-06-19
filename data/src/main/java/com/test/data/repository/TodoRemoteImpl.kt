package com.test.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.data.api.TodoApi
import com.test.data.entities.BuyEntity
import com.test.data.entities.CallEntity
import com.test.data.entities.SellEntity
import com.test.data.local.db.dao.TodoDao
import com.test.data.mapper.BuyMapper
import com.test.data.mapper.CallMapper
import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import com.test.domain.entities.CallItemResult
import com.test.domain.entities.CallResult
import io.reactivex.Flowable

class TodoRemoteImpl constructor(private val api: TodoApi, private val dao: TodoDao) :
    TodoDataStore {

    private val buyMapper = BuyMapper()
    private val callMapper = CallMapper()


    override fun getBuyList(): Flowable<BuyResult> {
        return api.getBuyList().map { tokenResponse ->
            var map: List<BuyItemResult> = emptyList()
            map = Gson().fromJson(tokenResponse, object : TypeToken<List<BuyItemResult>>() {}.type)

            val callEntity = BuyEntity.create(map)
            val model = buyMapper.mapToModel(callEntity)
            model
        }
    }

    override fun getCallList(): Flowable<CallResult> {
        return api.getCallList().map { tokenResponse ->
            var map: List<CallItemResult> = emptyList()
            map = Gson().fromJson(tokenResponse, object : TypeToken<List<CallItemResult>>() {}.type)
            val callEntity = CallEntity.create(map)
            val model = callMapper.mapToModel(callEntity)
            model
        }
    }

    override fun getSellList(): Flowable<BuyResult> {
        return dao.getSellList().map { dataList ->
            var map: MutableList<BuyItemResult> = mutableListOf()
            dataList.forEach { item ->
                map.add(BuyItemResult().apply {
                    this.id = item.id
                    this.price = item.price
                    this.name = item.name
                    this.quantity = item.quantity
                    this.type = item.type
                })
            }
            val callEntity = BuyEntity.create(map)
            val model = buyMapper.mapToModel(callEntity)

            model
        }
    }

    override fun addSellItem(sellItem: BuyItemResult) {
        val sellEntity = SellEntity(
            id = sellItem.id,
            name = sellItem.name,
            type = sellItem.type,
            quantity = sellItem.quantity,
            price = sellItem.price
        )
        dao.addSellData(sellEntity)
    }
}