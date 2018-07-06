@file:JvmName("SwipeTest")

package org.beatonma.lib.testing.espresso

import org.junit.Assert.assertEquals
import org.junit.Test

class SwipeTest {
    private val viewWidth = 100F
    private val viewHeight = 110F
    private val viewPosition = intArrayOf(50, 60)

    private val insetX = EDGE_FUZZ_FACTOR * viewWidth
    private val insetY = EDGE_FUZZ_FACTOR * viewHeight

    private fun mockEvent(x: Float, y: Float, allowOutOfBounds: Boolean): String {
        return getInsetCoordinates(
                x, y,
                viewPosition,
                insetX, insetY,
                viewWidth, viewHeight,
                allowOutOfBounds).joinToString()
    }

    @Test
    fun getCoordinatesProvider_forceInsideBounds_isCorrect() {
        // Event at view start - insets should be added
        assertEquals(
                floatArrayOf(viewPosition[0] + insetX,
                             viewPosition[1] + insetY).joinToString(),
                mockEvent(0F, 0F, allowOutOfBounds = false))

        // Event at view end - insets should be subtracted
        assertEquals(
                floatArrayOf(viewPosition[0] + viewWidth - insetX,
                             viewPosition[1] + viewHeight - insetY).joinToString(),
                mockEvent(1F, 1F, allowOutOfBounds = false))

        // Event at view center - insets should be ignored
        assertEquals(
                floatArrayOf(viewPosition[0] + (viewWidth / 2),
                             viewPosition[1] + (viewHeight / 2)).joinToString(),
                mockEvent(.5F, .5F, allowOutOfBounds = false)
        )
    }

    @Test
    fun getCoordinatesProvider_allowOutOfBounds_isCorrect() {
        // Event at view start
        assertEquals(
                floatArrayOf(viewPosition[0].toFloat(),
                             viewPosition[1].toFloat()).joinToString(),
                mockEvent(0F, 0F, allowOutOfBounds = true))

        // Event at view end
        assertEquals(
                floatArrayOf(viewPosition[0].toFloat() + viewWidth,
                             viewPosition[1].toFloat() + viewHeight).joinToString(),
                mockEvent(1F, 1F, allowOutOfBounds = true))

        // Event at view center
        assertEquals(
                floatArrayOf(viewPosition[0].toFloat() + (viewWidth / 2),
                             viewPosition[1].toFloat() + (viewHeight / 2)).joinToString(),
                mockEvent(.5F, .5F, allowOutOfBounds = true)
        )

        // Event after view bounds
        assertEquals(
                floatArrayOf(viewPosition[0].toFloat() + (viewWidth * 5),
                        viewPosition[1].toFloat() + (viewHeight * 5)).joinToString(),
                mockEvent(5F, 5F, allowOutOfBounds = true)
        )

        // Event before view bounds
        assertEquals(
                floatArrayOf(viewPosition[0].toFloat() + (-viewWidth),
                             viewPosition[1].toFloat() + (-viewHeight)).joinToString(),
                mockEvent(-1F, -1F, allowOutOfBounds = true)
        )
    }
}