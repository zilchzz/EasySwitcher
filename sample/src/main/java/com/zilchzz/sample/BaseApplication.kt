package com.zilchzz.sample

import android.app.Application
import com.zilchzz.library.widgets.EasySwitcher

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        EasySwitcher.setDefaultOpenBgColor("#FFFF00FF")
        EasySwitcher.setDefaultCloseBgColor("#FFF1F1F1")
        EasySwitcher.setDefaultAnimTime(350)
    }
}