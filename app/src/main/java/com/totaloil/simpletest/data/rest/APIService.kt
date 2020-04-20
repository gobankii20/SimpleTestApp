package com.totaloil.simpletest.data.rest

import com.totaloil.simpletest.data.rest.model.ModelLogin
import com.totaloil.simpletest.data.rest.model.response.ModelLogins
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @FormUrlEncoded
    @POST("login")
    fun doLoginUser(
        @Field("account") account: String,
        @Field("password") password: String): Observable<ModelLogins>


    @POST("ib_login")
    fun doReFreshToken(
        @Query("user") Username: String,
        @Query("pwd") Password: String): Single<ModelLogin>

}