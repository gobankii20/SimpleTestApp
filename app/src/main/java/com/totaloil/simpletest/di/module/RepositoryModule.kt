package com.totaloil.simpletest.di.module

import com.totaloil.simpletest.data.remote.repository.GeneralRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { GeneralRepository(get()) }

}