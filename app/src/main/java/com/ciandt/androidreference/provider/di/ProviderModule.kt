package com.ciandt.androidreference.provider.di

import com.ciandt.androidreference.provider.api.GistApiProvider
import com.ciandt.androidreference.provider.api.di.ApiModule
import com.ciandt.androidreference.provider.api.impl.GistApiProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(includes = [ApiModule::class])
interface ProviderModule {

    @Singleton
    @Binds
    fun bindGistApiProvider(provider: GistApiProviderImpl): GistApiProvider
}