package com.ciandt.androidreference.ui.di

import com.ciandt.androidreference.di.ApplicationModule
import com.ciandt.androidreference.di.FragmentModule
import com.ciandt.androidreference.tools.di.DaggerTestComponent
import com.ciandt.androidreference.ui.BaseTest
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        FragmentModule::class,
        ViewModelFactoryFakeModule::class]
)
interface ViewModelFactoryFakeComponent : DaggerTestComponent<BaseTest> {

    @Component.Builder
    abstract class Builder : DaggerTestComponent.Builder()
}


