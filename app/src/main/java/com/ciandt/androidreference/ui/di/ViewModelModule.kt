package com.ciandt.androidreference.ui.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ciandt.androidreference.business.di.BusinessModule
import com.ciandt.androidreference.ui.ViewModelFactory
import com.ciandt.androidreference.ui.publicGists.PublicGistsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BusinessModule::class])
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PublicGistsViewModel::class)
    fun bindPublicGistsViewModel(publicGistsViewModel: PublicGistsViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}