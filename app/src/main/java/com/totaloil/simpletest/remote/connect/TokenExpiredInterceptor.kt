package com.totaloil.simpletest.remote.connect

import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.Response


class TokenExpiredInterceptor : Interceptor {


    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        //You can get request and re-write it
        val request = chain.request()

        //Continue to invoke service and will resume this line when response coming in
        val response = chain.proceed(chain.request())

        if (response.code() == 401) {
            //Call refresh token service
            val refreshTokenResponse = HttpRetrofit.myAppService.doReFreshToken("", "").blockingGet()

            if (refreshTokenResponse.first_name.isNotEmpty()) {
                //Rewrite request of the service and call it gain
                //preferences.saveToken(refreshTokenResponse.first_name)
                val newRequest = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", refreshTokenResponse.first_name)
                    .build()
                return chain.proceed(newRequest) //return response with new token
            }
        }
        //Return response back to application
        return response
    }


}