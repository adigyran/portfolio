<<<<<<< HEAD:core/ui/core/src/androidTest/java/com/aya/digital/core/ui/core/ExampleInstrumentedTest.kt
package com.aya.digital.core.ui.core
=======
package com.aya.digital.core.ui.base
>>>>>>> master_new:core/ui/base/src/androidTest/java/com/aya/digital/core/ui/base/ExampleInstrumentedTest.kt

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
<<<<<<< HEAD:core/ui/core/src/androidTest/java/com/aya/digital/core/ui/core/ExampleInstrumentedTest.kt
        assertEquals("com.aya.digital.core.ui.core.test", appContext.packageName)
=======
        assertEquals("com.aya.digital.core.ui.base.test", appContext.packageName)
>>>>>>> master_new:core/ui/base/src/androidTest/java/com/aya/digital/core/ui/base/ExampleInstrumentedTest.kt
    }
}