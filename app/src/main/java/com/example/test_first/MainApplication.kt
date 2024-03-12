package com.example.test_first

import android.app.Application
import android.content.Context
import com.example.test_first.di.AppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    val Context.appComponent: AppComponent
        get() = when (this) {
            is MainApplication -> appComponent
            else -> (applicationContext as MainApplication).appComponent
        }
}