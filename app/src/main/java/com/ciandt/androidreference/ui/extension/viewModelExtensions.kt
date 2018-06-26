package com.ciandt.androidreference.ui.extension

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlin.coroutines.experimental.CoroutineContext

fun ViewModel.callAsync(context: CoroutineContext, block: suspend ViewModel.() -> Unit):
        Deferred<Unit> {
    return async(context) {
        block()
    }
}