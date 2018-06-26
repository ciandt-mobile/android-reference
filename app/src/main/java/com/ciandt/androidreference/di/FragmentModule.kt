package com.ciandt.androidreference.di

import com.ciandt.androidreference.ui.publicGists.PublicGistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector()
    fun publicGistsFragment(): PublicGistsFragment
}