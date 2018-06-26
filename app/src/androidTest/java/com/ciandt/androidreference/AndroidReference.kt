package com.ciandt.androidreference

import com.ciandt.androidreference.di.DaggerTestApplicationComponent
import com.ciandt.androidreference.di.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AndroidReference : DaggerApplication() {

    lateinit var component: TestApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component =
                DaggerTestApplicationComponent.builder().create(this) as TestApplicationComponent
        return component
    }
}