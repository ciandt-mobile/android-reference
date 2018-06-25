package com.ciandt.androidreference.infrastructure.logging.timber

import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (Fabric.isInitialized() && t != null) {
            Crashlytics.logException(t)
        }
    }
}