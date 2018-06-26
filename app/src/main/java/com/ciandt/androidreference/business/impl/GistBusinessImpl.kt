package com.ciandt.androidreference.business.impl

import com.ciandt.androidreference.business.GistBusiness
import com.ciandt.androidreference.business.data.Failure
import com.ciandt.androidreference.business.data.Resource
import com.ciandt.androidreference.business.data.Success
import com.ciandt.androidreference.entity.error.Error
import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.infrastructure.CoroutineExecutors
import com.ciandt.androidreference.provider.api.GistApiProvider
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class GistBusinessImpl @Inject constructor(private val gistApiProvider: GistApiProvider) :
    GistBusiness {

    override fun publicGists(): Deferred<Resource<Array<Gist>>> {
        return async(CoroutineExecutors.CommonPool) {
            try {
                Success(gistApiProvider.publicGists())
            } catch (e: Error) {
                Failure<Array<Gist>>(e)
            }
        }
    }
}