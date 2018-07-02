@file:JvmName("Espresso")

package org.beatonma.lib.testing.espresso

import android.util.Log
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.*
import org.beatonma.lib.util.kotlin.extensions.heightF
import org.beatonma.lib.util.kotlin.extensions.widthF
import org.junit.Assert
import kotlin.math.max
import kotlin.math.min

private const val EDGE_FUZZ_FACTOR = 0.06F

/**
 * Swipe vertically along the center-line of the view.
 */
fun swipeVertical(distance: Float = .25F,
                  startX: Float = .5F, startY: Float = .5F,
                  speed: Swipe = Swipe.FAST): ViewAction {
    return swipe(distanceY = distance, startX = startX, startY = startY, speed = speed)
}

/**
 * Swipe horizontally along the center-line of the view.
 */
fun swipeHorizontal(distance: Float = .25F,
                    startX: Float = .5F, startY: Float = .5F,
                    speed: Swipe = Swipe.FAST): ViewAction {
    return swipe(distanceX = distance, startX = startX, startY = startY, speed = speed)
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
    val xPx = max(insetX, xy[0].toFloat() + min(view.widthF - insetX, x * view.widthF))
    val yPx = max(insetY, xy[1].toFloat() + min(view.heightF - insetY, y * view.heightF))

    Log.d("coords", "($xPx, $yPx)")

    floatArrayOf(xPx, yPx)
}