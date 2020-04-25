package com.youtube.player.base

import android.content.Context

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext: Context = InstrumentationRegistry.getTargetContext()
        assertEquals("ja.burhanrashid52.base.test", appContext.getPackageName())
    }
}