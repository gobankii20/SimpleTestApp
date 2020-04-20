package com.totaloil.simpletest.data.rest.repository

import com.totaloil.simpletest.data.rest.APIService
import com.totaloil.simpletest.data.rest.connect.NetworkBoundResource
import com.totaloil.simpletest.data.rest.model.BodyLogin
import com.totaloil.simpletest.data.rest.model.response.ModelLogins
import io.reactivex.Observable

class GeneralRepository constructor(val apiService: APIService) {

    fun doLogin(paramLogin: BodyLogin) = object : NetworkBoundResource<ModelLogins>() {
        override fun createCall(): Observable<ModelLogins> = apiService.doLoginUser(paramLogin.user, paramLogin.pwd)
    }.asLiveData()
}