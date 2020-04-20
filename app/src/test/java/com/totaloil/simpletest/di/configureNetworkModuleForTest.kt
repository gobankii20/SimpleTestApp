package com.totaloil.simpletest.di

import com.totaloil.simpletest.data.rest.APIService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Network module test configuration with mockserver url.
 */
fun configureNetworkModuleForTest(baseApi: String)
        = module{
    single {
        Retrofit.Builder()
            .baseUrl(baseApi)
           .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    factory{ get<Retrofit>().create(APIService::class.java) }
}