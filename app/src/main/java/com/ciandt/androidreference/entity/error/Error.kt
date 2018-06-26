package com.ciandt.androidreference.entity.error

class Error(
    val type: ErrorType,
    override val message: String? = null,
    override val cause: Throwable? = null
) : RuntimeException()