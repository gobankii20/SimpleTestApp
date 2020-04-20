package com.totaloil.simpletest.data.rest.connect

import android.annotation.SuppressLint
import com.totaloil.simpletest.data.rest.APIService
import okhttp3.Interceptor
import okhttp3.Response


class TokenExpiredInterceptor : Interceptor {

    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code() == 401) {
            val refreshTokenResponse =
                RetrofitBuilder.build<APIService>("").doReFreshToken("", "").blockingGet()

            if (refreshTokenResponse.first_name.isNotEmpty()) {

                //preferences.saveToken(refreshTokenResponse.first_name)
                val newRequest = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", refreshTokenResponse.first_name)
                    .build()
                return chain.proceed(newRequest) //return response with new token
            }
        }
        return response
    }
}