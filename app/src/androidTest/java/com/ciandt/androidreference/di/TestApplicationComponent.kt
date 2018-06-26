package com.ciandt.androidreference.di

import com.ciandt.androidreference.AndroidReference
import com.ciandt.androidreference.ui.BaseTest
import com.ciandt.androidreference.ui.di.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        FragmentModule::class,
        ViewModelFactoryModule::class]
)
interface TestApplicationComponent : AndroidInjector<AndroidReference> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidReference>()

    fun injectTest(test: BaseTest)
}