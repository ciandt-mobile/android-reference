package com.ciandt.androidreference.ui

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ciandt.androidreference.AndroidReference
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactoryFake

    @Before
    fun inject() {
        MockitoAnnotations.initMocks(this)
        val app = InstrumentationRegistry.getTargetContext().applicationContext as AndroidReference
        app.component.injectTest(this)
    }
}