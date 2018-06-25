package com.ciandt.androidreference.provider.api.configuration

import android.content.Context
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.net.ssl.TrustManagerFactory

class ReleaseApiClient @Inject constructor(
    context: Context, headerInterceptor: Interceptor, trustManagerFactory: TrustManagerFactory,
    tlsSocketFactory: TLSSocketFactory
) : ApiClient(context, headerInterceptor, trustManagerFactory, tlsSocketFactory) {

    private val pinningCertificate: CertificatePinner = CertificatePinner.Builder().add(
        "*.github.com",
        "sha256/y2HhTRXXLdmAF1esYBb/muQUl3BIBdmEB8jUvMrGc28="
    ).build()

    override fun builder(): OkHttpClient.Builder = super.builder()
        .certificatePinner(pinningCertificate)
}