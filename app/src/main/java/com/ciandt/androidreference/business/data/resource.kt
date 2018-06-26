package com.ciandt.androidreference.business.data

import com.ciandt.androidreference.entity.error.Error

sealed class Resource<out T>
data class Success<out T>(val data: T) : Resource<T>()
data class Failure<out T>(val error: Error) : Resource<T>()