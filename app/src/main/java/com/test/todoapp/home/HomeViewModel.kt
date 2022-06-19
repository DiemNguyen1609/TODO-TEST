package com.test.todoapp.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.common.BaseViewModel
import com.test.common.entities.CommonDataEntity
import com.test.common.entities.CommonItem
import com.test.domain.entities.*
import com.test.domain.usecases.AddSellUseCase
import com.test.domain.usecases.GetBuyUseCase
import com.test.domain.usecases.GetCallUseCase
import com.test.domain.usecases.GetSellUseCase
import com.test.todoapp.R
import java.math.BigDecimal

class HomeViewModel(
    private val context: Context,
    private val callUseCase: GetCallUseCase,
    private val getSellUseCase: GetSellUseCase,
    private val buyUseCase: GetBuyUseCase,
    private val addSellUseCase: AddSellUseCase
) : BaseViewModel() {

    private val _callListLiveData = MutableLiveData<Event<Data<CallResult>>>()
    val callListLiveData: LiveData<Event<Data<CallResult>>> = _callListLiveData

    private val _buyListLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val buyListLiveData: LiveData<Event<Data<BuyResult>>> = _buyListLiveData

    private val _addSellLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val addSellLiveData: LiveData<Event<Data<BuyResult>>> = _addSellLiveData

    private val _getSellLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val getSellLiveData: LiveData<Event<Data<BuyResult>>> = _getSellLiveData


    fun getCallList() {
        val disposable = callUseCase.requestCallList()
            .doOnSubscribe {
                val result: Data<CallResult> = Data(Status.LOADING, null)
                _callListLiveData.value = Event(result)
            }
            .doOnComplete {

            }
            .subscribe({ response ->
                val data = Data(responseType = Status.SUCCESSFUL, data = response)
                _callListLiveData.value = Event(data)

            }, { error ->

                _callListLiveData.value =
                    Event(Data(responseType = Status.ERROR, error = Error(error.message)))
                handleNetworkError(error)
            }, {

            })

        addDisposable(disposable)
    }

    fun getBuyList() {
        val disposable = buyUseCase.requestBuyList()
            .doOnSubscribe {
                val result: Data<BuyResult> = Data(Status.LOADING, null)
                _buyListLiveData.value = Event(result)
            }
            .doOnComplete {

            }
            .subscribe({ response ->
                val data = Data(responseType = Status.SUCCESSFUL, data = response)
                _buyListLiveData.value = Event(data)

            }, { error ->

                _buyListLiveData.value =
                    Event(Data(responseType = Status.ERROR, error = Error(error.message)))
                handleNetworkError(error)
            }, {

            })

        addDisposable(disposable)
    }

    fun addSellList(data: BuyItemResult) {
        addSellUseCase.addSellList(data)
    }

    fun getSellList() {
        val disposable = getSellUseCase.getSellList()
            .doOnSubscribe {
                val result: Data<BuyResult> = Data(Status.LOADING, null)
                _getSellLiveData.postValue(Event(result))
            }
            .doOnComplete {

            }
            .subscribe({ response ->
                val data = Data(responseType = Status.SUCCESSFUL, data = response)
                _getSellLiveData.postValue(Event(data))

            }, { error ->

                _getSellLiveData.postValue(
                    Event(Data(responseType = Status.ERROR, error = Error(error.message)))
                )
                handleNetworkError(error)
            }, {

            })

        addDisposable(disposable)
    }

    fun parseBuyDataToCommonItem(data: MutableList<BuyItemResult>): MutableList<CommonDataEntity> {
        var dataResult: MutableList<CommonDataEntity> = mutableListOf()
        data.map { buyItem ->
            val commonDataEntity: CommonDataEntity = CommonDataEntity().apply {
                this.id = buyItem.id
                this.dataDetailList = listOf(
                    CommonItem(
                        value = context.getString(R.string.buy_list_name_item, buyItem.name),
                        color = com.test.common.R.color.red
                    ),
                    CommonItem(
                        value = context.getString(
                            R.string.buy_list_price_item,
                            buyItem.price.toString()
                        ),
                        color = com.test.common.R.color.yellow
                    ),
                    CommonItem(
                        value = context.getString(
                            R.string.buy_list_quantity_item,
                            buyItem.quantity.toString()
                        ),
                        color = R.color.greyish_brown
                    )
                )
            }
            dataResult.add(commonDataEntity)
        }
        return dataResult
    }

    fun parseCallDataToCommonItem(data: MutableList<CallItemResult>): MutableList<CommonDataEntity> {
        var dataResult: MutableList<CommonDataEntity> = mutableListOf()
        data.map { callItem ->
            val commonDataEntity: CommonDataEntity = CommonDataEntity().apply {
                this.id = callItem.id
                this.dataDetailList = listOf(
                    CommonItem(
                        value = context.getString(R.string.buy_list_name_item, callItem.name),
                        color = com.test.common.R.color.red
                    ),
                    CommonItem(
                        value = context.getString(
                            R.string.buy_list_price_item,
                            callItem.number
                        ),
                        color = R.color.greyish_brown
                    )
                )
            }
            dataResult.add(commonDataEntity)
        }
        return dataResult
    }

    fun addDataToDataBase() {

        addSellList(BuyItemResult().apply {
            this.id = 1
            this.price = BigDecimal.valueOf(1235456)
            this.name = "Test 1"
            this.type = 2
            this.quantity = 4
        })
        addSellList(BuyItemResult().apply {
            this.id = 2
            this.price = BigDecimal.valueOf(543453)
            this.name = "Test 2"
            this.type = 2
            this.quantity = 45
        })
        addSellList(BuyItemResult().apply {
            this.id = 3
            this.price = BigDecimal.valueOf(3356546)
            this.name = "Test 3"
            this.type = 2
            this.quantity = 67
        })

    }

}