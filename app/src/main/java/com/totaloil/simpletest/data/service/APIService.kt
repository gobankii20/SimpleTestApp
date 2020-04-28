package com.totaloil.simpletest.data.service

import com.totaloil.simpletest.data.remote.model.BodyLikeNews
import com.totaloil.simpletest.data.remote.model.BodyLogin
import com.totaloil.simpletest.data.remote.model.BodyRefreshToken
import com.totaloil.simpletest.data.remote.model.response.BaseResposne
import com.totaloil.simpletest.data.remote.model.response.ModelListNews
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface APIService {

    @POST("login")
    fun doLoginUser(
        @Body paramBody: BodyLogin
    ): Observable<BaseResposne>


    @GET("news")
    fun getListNews(
        @Header("Authorization") token: String
    ): Observable<ModelListNews>


    @POST("like")
    fun doLikeNews(
        @Header("Authorization") token: String,
        @Body paramBody: BodyLikeNews
    ): Observable<String>


    @POST("refresh")
    fun doReFreshToken(
        @Body paramBody: BodyRefreshToken
    ): Single<BaseResposne>

}