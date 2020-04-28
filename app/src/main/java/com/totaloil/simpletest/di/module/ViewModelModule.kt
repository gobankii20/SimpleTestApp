package com.totaloil.simpletest.di.module

import com.totaloil.simpletest.view.home.MainViewModel
import com.totaloil.simpletest.view.login.LoginViewModel
import com.totaloil.simpletest.view.newsDetail.NewDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { MainViewModel(get()) }

    viewModel { NewDetailViewModel(get()) }
}