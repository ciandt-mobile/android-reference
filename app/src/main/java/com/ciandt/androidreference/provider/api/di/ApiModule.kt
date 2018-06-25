package com.ciandt.androidreference.provider.api.di

import com.ciandt.androidreference.provider.api.GistApi
import com.ciandt.androidreference.provider.api.configuration.di.ApiClientModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ApiClientModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideGistApi(retrofit: Retrofit): GistApi = retrofit.create(GistApi::class.java)
}