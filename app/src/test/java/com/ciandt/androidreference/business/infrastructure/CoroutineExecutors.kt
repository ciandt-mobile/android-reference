package com.ciandt.androidreference.infrastructure

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

object CoroutineExecutors {

    val UI: CoroutineContext = Unconfined
    val CommonPool: CoroutineContext = Unconfined
}