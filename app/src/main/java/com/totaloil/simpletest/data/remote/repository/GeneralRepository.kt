package com.totaloil.simpletest.data.remote.repository

import com.totaloil.simpletest.data.service.APIService
import com.totaloil.simpletest.data.remote.NetworkBoundResource
import com.totaloil.simpletest.data.remote.model.BodyLikeNews
import com.totaloil.simpletest.data.remote.model.BodyLogin
import com.totaloil.simpletest.data.remote.model.response.BaseResposne
import com.totaloil.simpletest.data.remote.model.response.ModelListNews

class GeneralRepository constructor(val apiService: APIService) {

    fun doLogin(paramLogin: BodyLogin) = object : NetworkBoundResource<BaseResposne>() {
        override fun createCall() = apiService.doLoginUser(paramLogin)
    }.asLiveData()


    fun getListNews() = object : NetworkBoundResource<ModelListNews>() {
        override fun createCall() = apiService.getListNews("Bear xxx")
    }.asLiveData()


    fun doLikeNews(newsId:String) = object : NetworkBoundResource<String>() {
        override fun createCall() = apiService.doLikeNews("Bear xxx", BodyLikeNews(newsId))
    }.asLiveData()

}