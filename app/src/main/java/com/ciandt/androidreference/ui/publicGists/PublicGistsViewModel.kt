package com.ciandt.androidreference.ui.publicGists

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ciandt.androidreference.business.GistBusiness
import com.ciandt.androidreference.business.data.Success
import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.infrastructure.CoroutineExecutors
import com.ciandt.androidreference.ui.extension.callAsync
import javax.inject.Inject

class PublicGistsViewModel @Inject constructor(private val gistBusiness: GistBusiness) :
    ViewModel() {

    val gists: LiveData<Array<Gist>> = MutableLiveData<Array<Gist>>()

    fun init() {
        callAsync(CoroutineExecutors.UI) {
            val response = gistBusiness.publicGists().await()

            if (response is Success) {
                val data = response.data
                (gists as MutableLiveData).value = data
            }
        }
    }
}