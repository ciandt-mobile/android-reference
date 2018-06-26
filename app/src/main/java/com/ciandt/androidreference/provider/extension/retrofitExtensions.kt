package com.ciandt.androidreference.provider.extension

import com.ciandt.androidreference.entity.error.*
import com.ciandt.androidreference.infrastructure.logging.Logger
import com.google.gson.JsonParseException
import retrofit2.Call
import java.io.IOException

fun <T> Call<T>.callExecute(): T {
    try {
        val response = execute()
        if (!response.isSuccessful) {
            throw Error(Http(response.code(), response.errorBody()?.toString()), response.message())
        }

        return response.body() ?: throw Error(NullBody)
    } catch (e: JsonParseException) {
        Logger.e(e, e.message)
        throw Error(Conversion, e.message)
    } catch (e: IOException) {
        Logger.e(e, e.message)
        throw Error(Network, e.message)
    } catch (e: RuntimeException) {
        Logger.e(e, e.message)
        throw Error(Unexpected, e.message)
    }
}