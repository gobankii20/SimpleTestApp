package com.totaloil.simpletest.di.module

import com.totaloil.simpletest.data.rest.APIService
import com.totaloil.simpletest.data.rest.connect.OkHttpClientBuilder
import com.totaloil.simpletest.data.rest.connect.RetrofitBuilder
import com.totaloil.simpletest.data.rest.connect.TokenExpiredInterceptor
import org.koin.dsl.module

val networkModule = module {

    single { RetrofitBuilder }

    factory { TokenExpiredInterceptor() }

    single<APIService> { get<RetrofitBuilder>().build(OkHttpClientBuilder.getUrlServer()) }
}
