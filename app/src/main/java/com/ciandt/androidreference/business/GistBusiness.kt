package com.ciandt.androidreference.business

import com.ciandt.androidreference.business.data.Resource
import com.ciandt.androidreference.entity.gist.Gist
import kotlinx.coroutines.experimental.Deferred

interface GistBusiness {
    fun publicGists(): Deferred<Resource<Array<Gist>>>
}