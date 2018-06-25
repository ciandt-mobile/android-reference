package com.ciandt.androidreference.provider.api.configuration

import com.ciandt.androidreference.infrastructure.logging.Logger
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder().url(original.url().toString())

        val request = requestBuilder.build()

        val t1 = System.nanoTime()

        logRequest(request, chain)

        val response = chain.proceed(request)

        logResponse(response, t1)

        return response
    }

    private fun logResponse(response: Response, t1: Long) {

        try {
            val t2 = System.nanoTime()
            Logger.d(
                String.format(
                    "Retrofit Received response for ${response.request().url()} in %s ms%n%s",
                    (t2 - t1) / 1e6, response.headers()
                )
            )
        } catch (e: Exception) {
            Logger.d(e)
        }
    }

    private fun logRequest(request: Request, chain: Interceptor.Chain) {

        try {
            Logger.d(
                "Retrofit Sending request ${request.url()} " +
                        "on ${chain.connection()} ${request.headers()}"
            )

        } catch (e: Exception) {
            Logger.d(e)
        }
    }
}