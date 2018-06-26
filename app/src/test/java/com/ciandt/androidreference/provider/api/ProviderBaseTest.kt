package com.ciandt.androidreference.provider.api

import com.google.gson.Gson
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

abstract class ProviderBaseTest<out T : Any>(private val kclass: KClass<T>) {

    protected val mockWebServer = MockWebServer()

    private lateinit var _api: T

    protected val api : T
    get() {
        if (!::_api.isInitialized) {
            mockWebServer.start()

            _api = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(kclass.java)
        }

        return _api
    }
}