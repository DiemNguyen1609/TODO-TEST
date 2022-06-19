package com.test.todoapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.common.BaseViewModel
import com.test.domain.entities.*
import com.test.domain.usecases.AddSellUseCase
import com.test.domain.usecases.BuyUseCase

class HomeViewModel(
    private val callUseCase: BuyUseCase,
    private val addSellUseCase: AddSellUseCase
) : BaseViewModel() {

    private val _callListLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val callListLiveData: LiveData<Event<Data<BuyResult>>> = _callListLiveData

    private val _addSellLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val addSellLiveData: LiveData<Event<Data<BuyResult>>> = _addSellLiveData

    var callListResult: BuyResult = BuyResult()

    fun getCallList() {
        val disposable = callUseCase.requestSellList()
            .doOnSubscribe {

            }
            .doOnComplete {

            }
            .subscribe({ response ->
                val data = Data(responseType = Status.SUCCESSFUL, data = response)
                callListResult = response

                _callListLiveData.value = Event(data)

            }, { error ->

                _callListLiveData.value =
                    Event(Data(responseType = Status.ERROR, error = Error(error.message)))
                handleNetworkError(error)
            }, {

            })

        addDisposable(disposable)
    }

    fun addSellList(data: BuyItemResult) {
        addSellUseCase.addSellList(data)
        // handleNetworkError(error)
    }
}