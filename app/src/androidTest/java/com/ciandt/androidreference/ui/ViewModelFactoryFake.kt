package com.ciandt.androidreference.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactoryFake @Inject constructor() : ViewModelProvider.Factory {

    val viewModels = mutableMapOf<Class<out ViewModel>, ViewModel>()

    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        var viewModel: ViewModel? = viewModels[viewModelClass]

        if (viewModel == null) {
            for ((key, value) in viewModels) {
                if (viewModelClass.isAssignableFrom(key)) {
                    viewModel = value
                    break
                }
            }
        }

        if (viewModel == null) {
            throw IllegalArgumentException("unknown model class $viewModelClass")
        }

        try {
            return viewModelClass.cast(viewModel)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
