package com.totaloil.simpletest.data.remote

import com.totaloil.simpletest.data.locel.Constanst
import com.totaloil.simpletest.data.remote.model.BodyRefreshToken
import com.totaloil.simpletest.data.service.APIService
import okhttp3.Interceptor
import okhttp3.Response

class TokenExpiredInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code() == 401) {
            val refreshTokenResponse =
                RetrofitBuilder.build<APIService>(OkHttpClientBuilder.getUrlServer())
                    .doReFreshToken(BodyRefreshToken(Constanst.MOCK_TOKEN)).blockingGet()

            if (refreshTokenResponse.access_token.isNotEmpty()) {
                Constanst.MOCK_TOKEN = refreshTokenResponse.access_token
                val newRequest = chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", refreshTokenResponse.access_token)
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return response
    }
}