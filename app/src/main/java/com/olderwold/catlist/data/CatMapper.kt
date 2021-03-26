package com.olderwold.catlist.data

import com.olderwold.catlist.domain.Cat

class CatMapper {
    fun map(dto: CatDto): Cat? {
        val id = dto.id
        val url = dto.url

        return if (id != null && url != null) {
            if (id.isBlank() && url.isBlank()) {
                null
            } else {
                Cat(id = id, url = url)
            }
        } else {
            null
        }
    }
}
