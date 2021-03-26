package com.olderwold.catlist

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.olderwold.catlist.ui.CatListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class CatListActivityTest {
    @get:Rule
    var rule = ActivityScenarioRule(
        CatListActivity::class.java
    )

    @Test
    fun useAppContext() {
        Thread.sleep(TimeUnit.SECONDS.toMillis(5))
    }
}
