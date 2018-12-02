@file:JvmName("Click")

package org.beatonma.lib.testing.espresso.action

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions

/**
 * Tap at a custom point on the [View]
 */
fun click(x: Float = .5F, y: Float = .5F): ViewAction {
    return ViewActions.actionWithAssertions(
            GeneralClickAction(
                    Tap.SINGLE,
                    getCoordinatesProvider(x, y, allowOutOfBounds = true),
                    Press.FINGER, 0, 0))
}

/**
 * Long press at a custom point on the [View]
 */
fun longClick(x: Float = .5F, y: Float = .5F): ViewAction {
    return ViewActions.actionWithAssertions(
            GeneralClickAction(
                    Tap.LONG,
                    getCoordinatesProvider(x, y, allowOutOfBounds = true),
                    Press.FINGER, 0, 0))
}

/**
 * Double tap at a custom point on the [View]
 */
fun doubleClick(x: Float = .5F, y: Float = .5F): ViewAction {
    return ViewActions.actionWithAssertions(
            GeneralClickAction(
                    Tap.DOUBLE,
                    getCoordinatesProvider(x, y, allowOutOfBounds = true),
                    Press.FINGER, 0, 0))
}
