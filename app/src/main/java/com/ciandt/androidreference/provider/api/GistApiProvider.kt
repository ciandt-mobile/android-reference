package com.ciandt.androidreference.provider.api

import com.ciandt.androidreference.entity.gist.Gist

interface GistApiProvider {

    fun publicGists(): Array<Gist>
}