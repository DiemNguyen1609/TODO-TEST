package com.test.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.HttpException
import com.test.domain.entities.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.component.KoinComponent
import java.net.ConnectException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel(), KoinComponent {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }

    protected val _errorLiveData: MutableLiveData<Event<String>> = MutableLiveData()
    val errorLiveData: LiveData<Event<String>> = _errorLiveData

    protected fun handleNetworkError(e: Throwable) {
        //Todo pass context into this File.
        when (e) {
            is HttpException, is UnknownHostException, is ConnectException -> {
                _errorLiveData.value =  Event("Failed To Connect To server")
            }
            else -> {
                _errorLiveData.value = Event("System Error. Please try again later")

            }
        }
    }
}