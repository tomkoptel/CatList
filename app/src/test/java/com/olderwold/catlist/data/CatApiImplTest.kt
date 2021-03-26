package com.olderwold.catlist.data

import okreplay.*
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test

class CatApiImplTest {
    private val okReplayInterceptors = OkReplayInterceptor()
    private val okReplayConfig = OkReplayConfig.Builder()
        .interceptor(okReplayInterceptors)
        .defaultMode(TapeMode.READ_ONLY)
        .build()

    private val catApi = CatApiImpl {
        addInterceptor(okReplayInterceptors)
    }

    @get:Rule
    val rule = RecorderRule(okReplayConfig)

    @OkReplay(tape = "happy_case_cat_list")
    @Test
    fun `provide list of cats happy case`() {
        catApi.catList().test()
            .assertNoErrors()
            .assertValue { cats ->
                cats.shouldNotBeEmpty()

                val cat = cats.first()
                cat.id shouldBeEqualTo "7n2"
                cat.url shouldBeEqualTo "https://cdn2.thecatapi.com/images/7n2.jpg"
                true
            }
    }
}
