package com.olderwold.catlist.data

import org.junit.Test

class CatApiImplTest {
    @Test
    fun `provide list of cats happy case`() {
        val catApiImpl = CatApiImpl()
        catApiImpl.catList().test().assertNoErrors()
    }
}
