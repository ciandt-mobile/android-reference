package com.ciandt.androidreference.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[viewModelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (viewModelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class $viewModelClass")
        }
        try {
            return viewModelClass.cast(creator.get())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
