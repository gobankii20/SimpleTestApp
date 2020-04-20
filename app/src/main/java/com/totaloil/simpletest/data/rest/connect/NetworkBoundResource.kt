package com.totaloil.simpletest.data.rest.connect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.totaloil.simpletest.data.rest.connect.ApiResponse.onErrorResponseServer
import com.totaloil.simpletest.data.rest.model.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<RequestType> {

    private val result = MediatorLiveData<Resource<RequestType>>()

    @NonNull
    var disposable: Disposable? = null

    init {
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        disposable = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<RequestType>() {
                override fun onNext(response: RequestType) {
                    setValue(Resource.success(response))
                }

                override fun onError(response: Throwable) {
                    setValue(Resource.error(onErrorResponseServer(response)))
                }

                override fun onComplete() {
                }
            })
    }

    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    fun asLiveData() = result as LiveData<Resource<RequestType>>

    protected abstract fun createCall(): Observable<RequestType>

}