package com.test.data.repository

import android.util.Log
import com.google.gson.Gson
import com.test.data.api.TodoApi
import com.test.data.entities.CallEntity
import com.test.data.mapper.CallMapper
import com.test.domain.entities.CallResult
import io.reactivex.Flowable

class TodoRemoteImpl constructor(private val api: TodoApi) : TodoDataStore {

    private val callMapper = CallMapper()

    override fun getCallList(): Flowable<CallResult> {
        return api.getCallList().map { tokenResponse ->
            Log.e("TESTAPI", "$tokenResponse")
            var map: Map<String, Any> = HashMap()
            map = Gson().fromJson(tokenResponse, map.javaClass)

            val callEntity = CallEntity.create(map)
            val model = callMapper.mapToModel(callEntity)
            model
        }

    }
}