package com.olderwold.catlist.domain

import io.reactivex.Single

interface CatApi {
    fun catList(): Single<List<Cat>>
}
