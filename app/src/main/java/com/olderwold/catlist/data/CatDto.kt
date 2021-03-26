package com.olderwold.catlist.data

import com.google.gson.annotations.SerializedName

data class CatDto(
    @SerializedName("breeds")
    val breeds: List<Any?>? = null,
    @SerializedName("categories")
    val categories: List<Category?>? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
) {
    data class Category(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null
    )
}
