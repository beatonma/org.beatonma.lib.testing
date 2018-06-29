@file:JvmName("Espresso")

package org.beatonma.lib.testing.espresso

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions
import org.beatonma.lib.core.kotlin.extensions.heightF
import org.beatonma.lib.core.kotlin.extensions.widthF
import org.junit.Assert
import kotlin.math.max
import kotlin.math.min

private const val EDGE_FUZZ_FACTOR = 0.06F

/**
 * Swipe vertically along the center-line of the view.
 */
fun swipeVertical(distance: Float = .25F, startY: Float = .5F, speed: Swipe = Swipe.FAST): ViewAction {
    return swipe(distanceY = distance, startY = startY, speed = speed)
}

/**
 * Swipe horizontally along the center-line of the view.
 */
fun swipeHorizontal(distance: Float = .25F, startX: Float = .5F, speed: Swipe = Swipe.FAST): ViewAction {
    return swipe(distanceX = distance, startX = startX, speed = speed)
}

/**
 * Swipe from point (startX, startY) for a distance of (distanceX, distanceY)
 * All values are expressed as proportions of the view width/height
 */
fun swipe(
        distanceX: Float = 0F,
        distanceY: Float = 0F,
        startX: Float = .5f,
        startY: Float = .5F,
        speed: Swipe = Swipe.FAST
): ViewAction {
    val start = getCoordinatesProvider(x = startX, y = startY)
    val end = getCoordinatesProvider(x = startX + distanceX, y = startY + distanceY)

    Assert.assertNotEquals(start, end)
    return ViewActions.actionWithAssertions(
            GeneralSwipeAction(
                    speed,
                    start,
                    end,
                    Press.FINGER))
}


/**
 * Defaults to center of view
 */
internal fun getCoordinatesProvider(x: Float = .5F, y: Float = .5F) = CoordinatesProvider { view ->
    val insetX = EDGE_FUZZ_FACTOR * view.widthF
    val insetY = EDGE_FUZZ_FACTOR * view.heightF
    val xy = IntArray(2)
    view.getLocationOnScreen(xy)

    // Make sure that the coordinate is at least `inset` distance away from edge of view
    floatArrayOf(
            max(insetX, min(view.width - insetX, xy[0] + (x * view.widthF))),
            max(insetY, min(view.height - insetY, xy[1] + (y * view.heightF)))
    )
}