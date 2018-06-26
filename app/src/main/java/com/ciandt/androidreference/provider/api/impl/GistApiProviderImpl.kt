package com.ciandt.androidreference.provider.api.impl

import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.provider.api.GistApiProvider
import com.ciandt.androidreference.provider.api.GistApi
import com.ciandt.androidreference.provider.extension.callExecute
import javax.inject.Inject

class GistApiProviderImpl @Inject constructor(private val gistApi: GistApi) :
    GistApiProvider {

    override fun publicGists(): Array<Gist> = gistApi.public().callExecute()
}