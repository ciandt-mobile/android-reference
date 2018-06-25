package com.ciandt.androidreference.provider.api.configuration

import android.content.Context
import com.ciandt.androidreference.infrastructure.logging.Logger
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.net.ssl.TrustManagerFactory

class DebugApiClient @Inject constructor(
    context: Context, headerInterceptor: Interceptor, trustManagerFactory: TrustManagerFactory,
    tlsSocketFactory: TLSSocketFactory
) : ApiClient(context, headerInterceptor, trustManagerFactory, tlsSocketFactory) {

    override fun builder(): OkHttpClient.Builder = super.builder()
        .hostnameVerifier { _, _ -> true }
        .addInterceptor(logInterceptor())
        .addInterceptor(LoggingInterceptor())

    private fun logInterceptor() = HttpLoggingInterceptor(
        HttpLoggingInterceptor.Logger { message -> Logger.d(message) })
        .apply { level = HttpLoggingInterceptor.Level.BODY }
}
