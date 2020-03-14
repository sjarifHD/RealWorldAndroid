package com.sjarifhd.realworldandroid

import android.app.Application
import com.facebook.stetho.Stetho
import com.sjarifhd.realworldandroid.data.local.RealWorldDatabase
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(DebugTree())
        }

        RealWorldDatabase.getDatabase(this)
    }
}