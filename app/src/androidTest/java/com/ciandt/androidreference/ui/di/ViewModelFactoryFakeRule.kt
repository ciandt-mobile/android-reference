package com.ciandt.androidreference.ui.di

import com.ciandt.androidreference.tools.di.DaggerTestRule
import com.ciandt.androidreference.ui.BaseTest

class ViewModelFactoryFakeRule(test: BaseTest) :
    DaggerTestRule<BaseTest, ViewModelFactoryFakeComponent>(test, {
        DaggerViewModelFactoryFakeComponent.builder().create(it)
    })