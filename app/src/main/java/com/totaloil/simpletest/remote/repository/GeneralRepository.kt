package com.totaloil.simpletest.remote.repository

import com.totaloil.simpletest.remote.connect.HttpRetrofit
import com.totaloil.simpletest.remote.model.ModelLogin
import com.totaloil.simpletest.remote.connect.NetworkBoundResource
import com.totaloil.simpletest.remote.model.BodyLogin
import io.reactivex.Observable

object GeneralRepository{

    fun onLogin(paramLogin: BodyLogin) = object : NetworkBoundResource<ModelLogin>( ) {
        override fun createCall(): Observable<ModelLogin> =
            HttpRetrofit.myAppService.doLoginUser(paramLogin.user,paramLogin.pwd)
    }.asLiveData()

}