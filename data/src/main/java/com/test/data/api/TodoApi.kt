package com.test.data.api

import io.reactivex.Flowable
import retrofit2.http.GET

interface TodoApi {

    @GET("buy")
    fun getBuyList(): Flowable<String>

    @GET("call")
    fun getCallList(): Flowable<String>
}