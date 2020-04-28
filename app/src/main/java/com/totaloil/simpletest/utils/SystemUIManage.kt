package com.totaloil.simpletest.utils

import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SystemUIManage constructor(private val appCompatActivity: AppCompatActivity){

    fun hideSystemMenuBottom(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            appCompatActivity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        }else{
            appCompatActivity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}