@file:JvmName("Espresso")

package org.beatonma.lib.testing.kotlin.extensions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

import org.beatonma.lib.testing.espresso.action.click as clickAction
import org.beatonma.lib.testing.espresso.action.doubleClick as doubleClickAction
import org.beatonma.lib.testing.espresso.action.longClick as longClickAction

inline class ViewWithID(@IdRes val viewID: Int) {
    val view: ViewInteraction
        get() = onView(withId(viewID))

    fun perform(action: () -> ViewAction) {
        view.perform(action())
    }

    fun check(match: ViewAssertion) {
        view.check(match)
    }

    fun hasText(text: String) {
        check(matches(ViewMatchers.withText(text)))
    }
}
