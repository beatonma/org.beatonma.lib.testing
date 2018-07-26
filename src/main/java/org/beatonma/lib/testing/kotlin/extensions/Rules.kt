@file:JvmName("Rules")

package org.beatonma.lib.testing.kotlin.extensions

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import kotlin.reflect.KClass

/**
 * Convenience for ActivityTestRule(javaClass, initialTouchMode = true, launchActivity = true)
 */
val <T : Activity> KClass<T>.testRule: ActivityTestRule<T>
    get() = testRule(initialTouchMode = true, launchActivity = true)

/**
 * Convenience for ActivityTestRule(javaClass, initialTouchMode, launchActivity)
 */
fun <T : Activity> KClass<T>.testRule(
        initialTouchMode: Boolean = true,
        launchActivity: Boolean = true
): ActivityTestRule<T> {
    return ActivityTestRule(java, initialTouchMode, launchActivity)
}
