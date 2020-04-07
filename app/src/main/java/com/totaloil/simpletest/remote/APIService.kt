package com.totaloil.simpletest.remote

import com.totaloil.simpletest.remote.model.ModelLogin
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @POST("ib_login")
    fun doLoginUser(
        @Query("user") Username: String,
        @Query("pwd") Password: String): Observable<ModelLogin>


    @POST("ib_login")
    fun doReFreshToken(
        @Query("user") Username: String,
        @Query("pwd") Password: String): Single<ModelLogin>

}