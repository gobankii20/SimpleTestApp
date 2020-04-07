package com.totaloil.simpletest.connect

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HttpRetrofitTestHelper {

    companion object {
        @Throws(Exception::class)
        fun <T> createRetrofitService(host: String?, classTarget: Class<T>?): T {
        val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val httpClient = OkHttpClient.Builder()
            //httpClient.addInterceptor(logging)
            return Retrofit.Builder()
                .baseUrl(host)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(classTarget)
        }
    }
}