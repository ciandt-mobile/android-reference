package com.ciandt.androidreference.tools.di

import android.app.Application
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector

abstract class DaggerTestApplication : Application(), HasActivityInjector, HasFragmentInjector,
    HasSupportFragmentInjector {

    var injector: DaggerTestInjector? = null

    override fun activityInjector() = injector?.activityInjector
    override fun fragmentInjector() = injector?.fragmentInjector
    override fun supportFragmentInjector() = injector?.supportFragmentInjector
}