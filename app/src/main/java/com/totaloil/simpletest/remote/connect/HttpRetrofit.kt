package com.totaloil.simpletest.remote.connect

import com.totaloil.simpletest.BuildConfig
import com.totaloil.simpletest.data.locel.Constanst
import com.totaloil.simpletest.remote.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpRetrofit {

    private var mRetrofit: Retrofit? = null

    private fun getInstance(): Retrofit {
        if (mRetrofit == null) {

            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = getHttpLoggingInterceptor()
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
                    addInterceptor(interceptor)
                    addInterceptor(TokenExpiredInterceptor())
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
            }.build()

            mRetrofit = Retrofit.Builder()
                .baseUrl(getUrlServer())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }

        return mRetrofit as Retrofit
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor.Level? {
        return if (BuildConfig.DEBUG)
                 HttpLoggingInterceptor.Level.BODY
               else
                 HttpLoggingInterceptor.Level.NONE
    }

    private fun getUrlServer():String{
        return if (BuildConfig.DEBUG) Constanst.URL_DEV else Constanst.URL_PRO
    }

    val myAppService: APIService = getInstance().create(APIService::class.java)
}