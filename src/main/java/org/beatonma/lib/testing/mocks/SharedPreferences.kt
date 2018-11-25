@file:JvmName("SharedPreferences")

package org.beatonma.lib.testing.mocks

import android.content.SharedPreferences
import org.beatonma.lib.testing.kotlin.extensions.mock
import org.beatonma.lib.testing.kotlin.extensions.whenever
import org.mockito.Matchers

fun mockedSharedPreferences(
        intValue: Int = 31,
        booleanValue: Boolean = true,
        floatValue: Float = 2.5F,
        stringValue: String = "some_string"
): SharedPreferences {
    return mock<SharedPreferences>().apply {
        whenever(getBoolean(Matchers.anyString(), Matchers.anyBoolean())).then { booleanValue }
        whenever(getInt(Matchers.anyString(), Matchers.anyInt())).then { intValue }
        whenever(getString(Matchers.anyString(), Matchers.anyString())).then { stringValue }
        whenever(getFloat(Matchers.anyString(), Matchers.anyFloat())).then { floatValue }
    }
}

fun mockedSharedPreferencesEditor(fakePrefs: HashMap<String, Any?>): SharedPreferences.Editor {
    return mock<SharedPreferences.Editor>().apply {
        whenever(putBoolean(Matchers.anyString(), Matchers.anyBoolean())).then {
            fakePrefs.put(it.arguments[0] as String, it.arguments[1])
        }
        whenever(putString(Matchers.anyString(), Matchers.anyString())).then {
            fakePrefs.put(it.arguments[0] as String, it.arguments[1])
        }
        whenever(putInt(Matchers.anyString(), Matchers.anyInt())).then {
            fakePrefs.put(it.arguments[0] as String, it.arguments[1])
        }
        whenever(putFloat(Matchers.anyString(), Matchers.anyFloat())).then {
            fakePrefs.put(it.arguments[0] as String, it.arguments[1])
        }
    }
}
