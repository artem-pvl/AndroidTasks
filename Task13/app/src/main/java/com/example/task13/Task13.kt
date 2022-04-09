package com.example.task13

import android.app.Application
import timber.log.Timber

class Task13 : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}