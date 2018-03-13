package com.stl.tpalt.redorblack

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.logging.Logger

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.stl.tpalt.redorblack", appContext.packageName)

        val deck = RedOrBlackApp.buildDeck()
        Log.e("error",deck.toString())
        assert(!deck.isEmpty() && deck.size == 52)
    }
}
