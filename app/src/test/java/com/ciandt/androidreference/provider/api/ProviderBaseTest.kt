package com.ciandt.androidreference.provider.api

import com.google.gson.Gson
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

abstract class ProviderBaseTest<out T : Any>(kclass: KClass<T>) {

    protected val mockWebServer = MockWebServer()
    protected val api: T

    init {
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(kclass.java)
    }
}