@file:JvmName("Exceptions")

package org.beatonma.lib.testing

import android.util.Log
import kotlin.reflect.KClass

/**
 * Catch all exceptions
 * @param catch     If not null, only catch the exception types given in this array
 * @param raise     If not null, catch any exception types unless they are in this array
 */
inline fun catchAll(
        tag: String? = null,
        message: String? = null,
        logLevel: Int = Log.WARN,
        catch: Array<KClass<out Throwable>>? = null,
        raise: Array<KClass<out Throwable>>? = null,
        block: () -> Unit
) {
    try {
        block()
    } catch (t: Throwable) {
        // Neither [catch] nor [raise] are defined so catch any exception
        if (catch == null && raise == null) {
            message?.let { Log.println(logLevel, tag, "$message: $t") }
            return
        }

        // Catch exception if its class is included in [catch] array
        if (catch?.contains(t::class) == true) {
            message?.let { Log.println(logLevel, tag, "$message: $t") }
            return
        }

        // Catch exception if its class is NOT included in [raise] array
        if (raise?.contains(t::class) == false) {
            message?.let { Log.println(logLevel, tag, "$message: $t") }
            return
        }

        // Raise exception if it has not been handled above
        throw t
    }
}
