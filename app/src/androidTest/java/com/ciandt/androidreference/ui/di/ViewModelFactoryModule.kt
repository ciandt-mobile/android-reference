package com.ciandt.androidreference.ui.di

import android.arch.lifecycle.ViewModelProvider
import com.ciandt.androidreference.ui.ViewModelFactoryFake
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactoryFake): ViewModelProvider.Factory
}