package com.ciandt.androidreference.provider.api.configuration

import android.content.Context
import com.ciandt.androidreference.R
import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

abstract class ApiClient(
    private val context: Context,
    private val headerInterceptor: Interceptor,
    private val trustManagerFactory: TrustManagerFactory,
    private val tlsSocketFactory: TLSSocketFactory
) {

    private val defaultTimeout =
        context.resources.getInteger(R.integer.default_api_timeout).toLong()

    private val fastTimeout =
        context.resources.getInteger(R.integer.default_api_timeout_fast).toLong()

    private val cacheSize = 10_485_760 // 10 MB

    protected open fun builder() = OkHttpClient.Builder().apply {
        val httpCacheDirectory = File(context.cacheDir, "okHttpCache")
        val localCache = Cache(httpCacheDirectory, cacheSize.toLong())

        addInterceptor(headerInterceptor)
        sslSocketFactory(
            tlsSocketFactory,
            trustManagerFactory.trustManagers[0] as X509TrustManager
        )
        cache(localCache)

        connectionSpecs(
            listOf(
                ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2).build()
            )
        )

        timeout()
    }

    protected open fun fastTimeoutBuilder(): OkHttpClient.Builder = builder().timeout(fastTimeout)

    private fun OkHttpClient.Builder.timeout(timeout: Long = defaultTimeout) = this.apply {
        connectTimeout(timeout, TimeUnit.SECONDS)
        readTimeout(timeout, TimeUnit.SECONDS)
        writeTimeout(timeout, TimeUnit.SECONDS)
    }

    fun client(): OkHttpClient {
        return builder().build()
    }

    fun clientFastTimeout(): OkHttpClient {
        return builder().build()
    }
}