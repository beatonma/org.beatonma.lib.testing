@file:JvmName("Rules")

package org.beatonma.lib.testing.kotlin.extensions

import android.app.Activity
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import kotlin.reflect.KClass

/**
 * Convenience for ActivityTestRule(javaClass, initialTouchMode = true, launchActivity = true)
 */
val <T : Activity> KClass<T>.testRule: ActivityTestRule<T>
    get() = testRule(initialTouchMode = true, launchActivity = true)

/**
 * Convenience for ActivityTestRule(javaClass, initialTouchMode, launchActivity)
 * You may also alter the intent that is used to launch the activity by providing an intentBlock
 */
fun <T : Activity> KClass<T>.testRule(
        initialTouchMode: Boolean = true,
        launchActivity: Boolean = true,
        intentBlock: ((Intent) -> Unit)? = null
): ActivityTestRule<T> {
    return if (intentBlock == null) ActivityTestRule(java, initialTouchMode, launchActivity)
    else {
        object: ActivityTestRule<T>(java, initialTouchMode, launchActivity) {
            override fun getActivityIntent(): Intent {
                return Intent(InstrumentationRegistry.getContext(), this@testRule.java).apply {
                    intentBlock(this)
                }
            }
        }
    }
}
