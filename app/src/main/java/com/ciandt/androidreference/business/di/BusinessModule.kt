package com.ciandt.androidreference.business.di

import com.ciandt.androidreference.business.GistBusiness
import com.ciandt.androidreference.business.impl.GistBusinessImpl
import com.ciandt.androidreference.provider.di.ProviderModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ProviderModule::class])
interface BusinessModule {

    @Binds
    @Singleton
    fun bindGistBusiness(business: GistBusinessImpl): GistBusiness
}