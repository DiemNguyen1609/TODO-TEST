package com.test.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.data.api.TodoApi
import com.test.data.entities.BuyEntity
import com.test.data.mapper.BuyMapper
import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.BuyResult
import io.reactivex.Flowable

class TodoRemoteImpl constructor(private val api: TodoApi) : TodoDataStore {

    private val callMapper = BuyMapper()

    override fun getCallList(): Flowable<BuyResult> {
        return api.getCallList().map { tokenResponse ->
            Log.e("TESTAPI", "$tokenResponse")
            var map: List<BuyItemResult> = emptyList()
            map = Gson().fromJson(tokenResponse, object : TypeToken<List<BuyItemResult>>() {}.type)

            val callEntity = BuyEntity.create(map)
            val model = callMapper.mapToModel(callEntity)
            model
        }

    }
}