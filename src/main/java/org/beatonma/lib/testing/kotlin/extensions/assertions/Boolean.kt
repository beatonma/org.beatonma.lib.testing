@file:JvmName("Boolean")
package org.beatonma.lib.testing.kotlin.extensions.assertions

import org.junit.Assert as JUnit

/**
 * Convenience for assertTrue([this])
 */
fun Boolean?.assert(message: String? = null) {
    this.assertTrue(message)
}

/**
 * Convenience for assertTrue([this])
 */
fun Boolean?.assertTrue(message: String? = null) {
    this?.let { JUnit.assertTrue(message, this) }
            ?: throw NullReceiverException("assertTrue", message)
}

/**
 * Convenience for assertFalse([this])
 */
fun Boolean?.assertFalse(message: String? = null) {
    this?.let { JUnit.assertFalse(message, this) }
            ?: throw NullReceiverException("assertFalse", message)
}