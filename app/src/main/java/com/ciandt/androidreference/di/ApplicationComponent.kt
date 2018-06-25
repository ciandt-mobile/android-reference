package com.ciandt.androidreference.di

import com.ciandt.androidreference.AndroidReference
import com.ciandt.androidreference.provider.api.di.ApiModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationBinders::class,
        ApiModule::class,
        ApplicationModule::class]
)
interface ApplicationComponent : AndroidInjector<AndroidReference> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidReference>()
}