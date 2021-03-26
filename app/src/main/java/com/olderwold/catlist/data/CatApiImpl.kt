package com.olderwold.catlist.data

import com.olderwold.catlist.domain.Cat
import com.olderwold.catlist.domain.CatApi
import io.reactivex.Single

class CatApiImpl : CatApi {
    override fun catList(): Single<List<Cat>> {
        TODO("Not yet implemented")
    }
}
