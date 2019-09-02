package pl.dopieralad.university.ma.flowers


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("pl.dopieralad.university.ma.flowers", appContext.packageName)
    }
}
