package com.totaloil.simpletest.data.remote

import com.totaloil.simpletest.BuildConfig
import com.totaloil.simpletest.data.locel.Constanst
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientBuilder {

    fun okHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            getHttpLoggingInterceptor()

        return OkHttpClient.Builder().addInterceptor {
            val original: Request = it.request()
            val request: Request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build()
            return@addInterceptor it.proceed(request)
        }.addInterceptor(interceptor)
            .addInterceptor(TokenExpiredInterceptor())
            .connectTimeout(Constanst.TIME_CONNECT, TimeUnit.SECONDS)
            .readTimeout(Constanst.TIME_CONNECT, TimeUnit.SECONDS)
            .build()

    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    fun getUrlServer(): String {
        return if (BuildConfig.DEBUG) Constanst.URL_DEV else Constanst.URL_PRO
    }
}