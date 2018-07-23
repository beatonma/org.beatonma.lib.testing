package org.beatonma.lib.testing.kotlin.extensions.assertions

import androidx.test.filters.SmallTest
import org.junit.Test

@SmallTest
class AnyTest {
    @Test
    fun kClass_assertInstanceOf_isCorrect() {
        "".assertInstanceOf(String::class)
        3.assertInstanceOf(Int::class)

        assertThrowsAssertionError { "".assertInstanceOf(Int::class) }
    }

    @Test
    fun kClass_assertNotInstanceOf_isCorrect() {
        "".assertNotInstanceOf(Int::class)
        3.assertNotInstanceOf(String::class)
    }

    @Test
    fun javaClass_assertInstanceOf_isCorrect() {
        "".assertInstanceOf(String::class.java)
        3.assertInstanceOf(Integer::class.java)

        assertThrowsAssertionError { "".assertInstanceOf(Int::class.java) }
    }

    @Test
    fun javaClass_assertNotInstanceOf_isCorrect() {
        "".assertNotInstanceOf(Integer::class.java)
        3.assertNotInstanceOf(String::class.java)
    }
}
