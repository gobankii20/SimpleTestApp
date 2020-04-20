package com.totaloil.simpletest.di.module

import com.totaloil.simpletest.data.rest.repository.GeneralRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { GeneralRepository(get()) }

}