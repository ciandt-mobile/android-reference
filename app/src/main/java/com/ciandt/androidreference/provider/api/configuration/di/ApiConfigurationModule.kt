package com.ciandt.androidreference.provider.api.configuration.di

import com.ciandt.androidreference.provider.api.configuration.ApiClient
import com.ciandt.androidreference.provider.api.configuration.ApiEndPoints
import com.ciandt.androidreference.provider.api.configuration.TLSSocketFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import javax.inject.Singleton
import javax.net.ssl.TrustManagerFactory

@Module
class ApiConfigurationModule {

    @Provides
    @Singleton
    fun providesHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val builder = original.newBuilder()

        builder.addHeader("Content-Type", "application/json")

        builder.method(original.method(), original.body())
        chain.proceed(builder.build())
    }

    @Provides
    @Singleton
    fun providesTrustManagerFactory(): TrustManagerFactory = TrustManagerFactory.getInstance(
        TrustManagerFactory.getDefaultAlgorithm()
    ).apply {
        init(null as KeyStore?)
    }

    @Provides
    @Singleton
    fun provideTLSSocketFactory() = TLSSocketFactory()


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: ApiClient, gson: Gson,
        apiEndPoints: ApiEndPoints
    ): Retrofit {

        System.setProperty("http.keepAlive", "false")

        return Retrofit.Builder()
            .baseUrl(apiEndPoints.url)
            .client(client.client())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}