package com.ciandt.androidreference.infrastructure

import kotlin.coroutines.experimental.CoroutineContext

object CoroutineExecutors {

    val UI: CoroutineContext = kotlinx.coroutines.experimental.android.UI
    val CommonPool: CoroutineContext = kotlinx.coroutines.experimental.CommonPool
}