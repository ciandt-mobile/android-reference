package com.ciandt.androidreference.ui.publicGists

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import com.ciandt.androidreference.business.GistBusiness
import com.ciandt.androidreference.business.data.Success
import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.ui.BaseTest
import com.ciandt.androidreference.ui.MainActivity
import kotlinx.coroutines.experimental.async
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class PublicGistsTest : BaseTest() {

    @Mock
    lateinit var business: GistBusiness

    private val gists = arrayOf(Gist("test"))

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    @LargeTest
    fun test() {
        `when`(business.publicGists()).thenReturn(async { Success(gists) })

        viewModelFactory.viewModels.clear()
        viewModelFactory.viewModels[PublicGistsViewModel::class.java] =
                PublicGistsViewModel(business)
        activityRule.launchActivity(Intent())

        onView(withText("test")).check(matches(isDisplayed()))
    }
}