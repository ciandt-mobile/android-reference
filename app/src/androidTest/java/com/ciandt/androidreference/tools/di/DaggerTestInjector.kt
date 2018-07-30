package com.ciandt.androidreference.tools.di

import android.app.Activity
import android.app.Fragment
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class DaggerTestInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var supportFragmentInjector:
            DispatchingAndroidInjector<android.support.v4.app.Fragment>
}