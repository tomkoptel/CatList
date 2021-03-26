package com.olderwold.catlist.data

import com.olderwold.catlist.domain.Cat
import com.olderwold.catlist.domain.CatApi
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class CatApiImpl private constructor(
    private val api: Api
) : CatApi {

    companion object Factory {
        operator fun invoke(block: OkHttpClient.Builder.() -> Unit = {}): CatApi {
            val client = OkHttpClient.Builder()
                .apply(block)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.thecatapi.com")
                .build()
            val api = retrofit.create(Api::class.java)
            return CatApiImpl(api)
        }
    }

    override fun catList(): Single<List<Cat>> {
        return api.list(limit = 5)
            .map { listOf<Cat>() }
    }

    // https://api.thecatapi.com/v1/images/search?limit=5
    private interface Api {
        @GET("/v1/images/search")
        fun list(@Query("limit") limit: Int): Single<List<CatDto>>
    }
}
