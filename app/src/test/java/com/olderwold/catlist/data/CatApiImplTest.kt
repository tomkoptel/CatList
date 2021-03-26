package com.olderwold.catlist.data

import okreplay.*
import org.junit.Rule
import org.junit.Test

class CatApiImplTest {
    private val okReplayInterceptors = OkReplayInterceptor()
    private val okReplayConfig = OkReplayConfig.Builder()
        .interceptor(okReplayInterceptors)
        .defaultMode(TapeMode.WRITE_ONLY)
        .build()

    private val catApi = CatApiImpl {
        addInterceptor(okReplayInterceptors)
    }

    @get:Rule
    val rule = RecorderRule(okReplayConfig)

    @OkReplay(tape = "happy_case_cat_list")
    @Test
    fun `provide list of cats happy case`() {
        catApi.catList().test().assertNoErrors()
    }
}
