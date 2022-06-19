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
import com.test.todoapp.R

class HomeViewModel(
    private val context: Context,
    private val callUseCase: GetCallUseCase,
    private val buyUseCase: GetBuyUseCase,
    private val addSellUseCase: AddSellUseCase
) : BaseViewModel() {

    private val _callListLiveData = MutableLiveData<Event<Data<CallResult>>>()
    val callListLiveData: LiveData<Event<Data<CallResult>>> = _callListLiveData

    private val _buyListLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val buyListLiveData: LiveData<Event<Data<BuyResult>>> = _buyListLiveData

    private val _addSellLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val addSellLiveData: LiveData<Event<Data<BuyResult>>> = _addSellLiveData


    fun getCallList() {
        val disposable = callUseCase.requestCallList()
            .doOnSubscribe {

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


}