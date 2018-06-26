package com.ciandt.androidreference.di

import com.ciandt.androidreference.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ApplicationBinders {

    @ContributesAndroidInjector()
    fun mainActivity(): MainActivity
}