package com.totaloil.simpletest.di.module

import androidx.appcompat.app.AppCompatActivity
import com.totaloil.simpletest.utils.DateManage
import com.totaloil.simpletest.utils.DialogPresenter
import com.totaloil.simpletest.utils.SystemUIManage
import org.koin.dsl.module

val utilityModule = module {

    //    single { Preferences(androidApplication()) }

    factory { (activity: AppCompatActivity) -> DialogPresenter(activity) }

    factory { (activity: AppCompatActivity) -> SystemUIManage(activity) }

    single { DateManage() }

//    factory { (activity: AppCompatActivity) -> DialogPresenter(activity) }
//
//    factory { (activity: AppCompatActivity) -> CheckPermission(activity) }
}
