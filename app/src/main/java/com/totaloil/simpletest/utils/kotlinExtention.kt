package com.totaloil.simpletest.utils

import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.hideSystemMenuBottom() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
    } else {
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}