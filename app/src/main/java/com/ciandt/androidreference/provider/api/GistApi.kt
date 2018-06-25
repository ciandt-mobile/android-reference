package com.ciandt.androidreference.provider.api

import com.ciandt.androidreference.entity.gist.Gist
import retrofit2.Call
import retrofit2.http.GET

interface GistApi {
    @GET("gists/public")
    fun public(): Call<Array<Gist>>
}