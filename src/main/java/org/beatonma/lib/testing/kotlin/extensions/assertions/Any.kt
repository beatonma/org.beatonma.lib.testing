@file:JvmName("Any")
package org.beatonma.lib.testing.kotlin.extensions.assertions

import org.junit.Assert as JUnit


internal class NullReceiverException(
        methodName: String, message: String?
) : AssertionError("[$message] Receiver is null on method '$methodName'")

/**
 * Convenience for [JUnit.assertNull]([this])
 */
fun Any?.assertNull(message: String? = null) {
    JUnit.assertNull(message, this)
}

/**
 * Convenience for assertNotNull([this])
 */
fun Any?.assertNotNull(message: String? = null) {
    JUnit.assertNotNull(message, this)
}

/**
 * Convenience for assertEquals([expected], [this])
 */
fun Any?.assertEquals(expected: Any?, message: String? = null) {
    JUnit.assertEquals(message, expected, this)
}

/**
 * Convenience for assertNotEquals(unexpected, [this])
 */
fun Any?.assertNotEquals(unexpected: Any?, message: String? = null) {
    JUnit.assertNotEquals(message, unexpected, this)
}
