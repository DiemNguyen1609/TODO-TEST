package com.test.todoapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.common.BaseViewModel
import com.test.domain.entities.BuyResult
import com.test.domain.entities.Data
import com.test.domain.entities.Event
import com.test.domain.entities.Status
import com.test.domain.usecases.BuyUseCase

class HomeViewModel(private val callUseCase: BuyUseCase) : BaseViewModel() {

    private val _callListLiveData = MutableLiveData<Event<Data<BuyResult>>>()
    val callListLiveData: LiveData<Event<Data<BuyResult>>> = _callListLiveData

    var callListResult: BuyResult = BuyResult()

    fun getCallList() {
        val disposable = callUseCase.requestDiscover()
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

}