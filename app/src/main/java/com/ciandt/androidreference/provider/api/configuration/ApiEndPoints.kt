package com.ciandt.androidreference.provider.api.configuration

import android.content.Context
import com.ciandt.androidreference.R
import javax.inject.Inject

class ApiEndPoints @Inject constructor(context: Context) {

    val url: String = context.getString(R.string.url_api)
}