package com.test.todoapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.common.BaseViewModel
import com.test.domain.entities.CallResult
import com.test.domain.entities.Data
import com.test.domain.entities.Event
import com.test.domain.entities.Status
import com.test.domain.usecases.CallUseCase

class HomeViewModel(private val callUseCase: CallUseCase) : BaseViewModel() {

    private val _callListLiveData = MutableLiveData<Event<Data<CallResult>>>()
    val callListLiveData: LiveData<Event<Data<CallResult>>> = _callListLiveData

    var callListResult: CallResult = CallResult()

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