package com.totaloil.simpletest.connect.service

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET


interface BlogGateway {

    @GET("/posts/v2")
    fun getListData(): Call<JSONObject>

}