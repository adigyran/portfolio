<<<<<<< HEAD:core/ui/base/src/androidTest/java/com/aya/digital/core/ui/base/ExampleInstrumentedTest.kt
package com.aya.digital.core.ui.base
=======
package com.aya.digital.core.mappers.doctors
>>>>>>> f434df5 (many architecture fixes):core/mappers/doctors/src/androidTest/java/com/aya/digital/core/mappers/doctors/ExampleInstrumentedTest.kt

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
<<<<<<< HEAD:core/ui/base/src/androidTest/java/com/aya/digital/core/ui/base/ExampleInstrumentedTest.kt
        assertEquals("com.aya.digital.core.ui.base.test", appContext.packageName)
=======
        assertEquals("com.aya.digital.core.mappers.doctors.test", appContext.packageName)
>>>>>>> f434df5 (many architecture fixes):core/mappers/doctors/src/androidTest/java/com/aya/digital/core/mappers/doctors/ExampleInstrumentedTest.kt
    }
}