package com.ciandt.androidreference.provider.api.configuration.di

import com.ciandt.androidreference.provider.api.configuration.ApiClient
import com.ciandt.androidreference.provider.api.configuration.ReleaseApiClient
import dagger.Binds
import dagger.Module

@Module(includes = [ApiConfigurationModule::class])
interface ApiClientModule {

    @Binds
    fun bindApiClient(apiClient: ReleaseApiClient): ApiClient
}