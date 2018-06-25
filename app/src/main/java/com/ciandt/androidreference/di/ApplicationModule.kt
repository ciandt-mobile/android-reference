package com.ciandt.androidreference.di

import android.content.Context
import com.ciandt.androidreference.AndroidReference
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ApplicationModule {

    @Singleton
    @Binds
    fun bindContext(context: AndroidReference): Context
}