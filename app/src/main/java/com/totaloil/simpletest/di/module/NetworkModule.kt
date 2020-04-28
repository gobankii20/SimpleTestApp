package com.totaloil.simpletest.di.module

import com.totaloil.simpletest.data.service.APIService
import com.totaloil.simpletest.data.remote.OkHttpClientBuilder
import com.totaloil.simpletest.data.remote.RetrofitBuilder
import com.totaloil.simpletest.data.remote.TokenExpiredInterceptor
import org.koin.dsl.module

val networkModule = module {

    single { RetrofitBuilder }

    factory { TokenExpiredInterceptor() }

    single<APIService> { get<RetrofitBuilder>().build(
        OkHttpClientBuilder.getUrlServer()) }
}
