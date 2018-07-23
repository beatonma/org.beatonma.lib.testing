@file:JvmName("Any")
package org.beatonma.lib.testing.kotlin.extensions.assertions

import kotlin.reflect.KClass
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

/**
 * Assert that the receiver is an instance of the given Kotlin class
 */
fun Any?.assertInstanceOf(cls: KClass<*>, message: String? = null) {
    JUnit.assertTrue(message, cls.isInstance(this))
}

/**
 * Assert that the receiver is an instance of the given Java class
 */
fun Any?.assertInstanceOf(cls: Class<*>, message: String? = null) {
    JUnit.assertTrue(message, cls.isInstance(this))
}

/**
 * Assert that the receiver is not an instance of the given Kotlin class
 */
fun Any?.assertNotInstanceOf(cls: KClass<*>, message: String? = null) {
    JUnit.assertFalse(message, cls.isInstance(this))
}

/**
 * Assert that the receiver is not an instance of the given Java class
 */
fun Any?.assertNotInstanceOf(cls: Class<*>, message: String? = null) {
    JUnit.assertFalse(message, cls.isInstance(this))
}
