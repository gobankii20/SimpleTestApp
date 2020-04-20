package com.totaloil.simpletest.data.rest.connect

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    inline fun <reified T> build(urlServer: String): T {
        return Retrofit.Builder()
            .baseUrl(urlServer)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClientBuilder.okHttpClient())
            .build()
            .create(T::class.java)
    }
}