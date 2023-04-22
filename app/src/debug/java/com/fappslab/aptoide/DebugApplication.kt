package com.fappslab.aptoide

import com.fappslab.aptoide.debugtools.FlipperSetup
import timber.log.Timber

class DebugApplication : ReleaseApplication() {

    override fun onCreate() {
        super.onCreate()
        FlipperSetup.start()
        Timber.plant(Timber.DebugTree())
    }
}
