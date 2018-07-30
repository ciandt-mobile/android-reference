package com.ciandt.androidreference.tools.di

import android.support.test.InstrumentationRegistry
import dagger.android.AndroidInjector
import org.junit.rules.TestWatcher
import org.junit.runner.Description

typealias DaggerTestInjectorBlock = (DaggerTestInjector) -> AndroidInjector<DaggerTestInjector>

open class DaggerTestRule<TTest, TComponent : DaggerTestComponent<TTest>>(
    private val test: TTest,
    block: DaggerTestInjectorBlock
) :
    TestWatcher() {

    private val injector = DaggerTestInjector()

    private val component: TComponent

    private val testApplication =
        InstrumentationRegistry.getTargetContext().applicationContext as DaggerTestApplication

    init {
        @Suppress("UNCHECKED_CAST")
        component = block(injector) as TComponent
    }

    override fun starting(description: Description?) {
        testApplication.injector = injector
        component.inject(injector)
        component.injectTest(test)

    }

    override fun finished(description: Description?) {
        testApplication.injector = null
    }
}
