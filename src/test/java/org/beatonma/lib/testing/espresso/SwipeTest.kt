@file:JvmName("SwipeTest")

package org.beatonma.lib.testing.espresso

import org.junit.Assert.assertEquals
import org.junit.Test

class SwipeTest {
    @Test
    fun getCoordinatesProvider_isCorrect() {
        val viewWidth = 100F
        val viewHeight = 110F
        val viewPosition = intArrayOf(50, 60)

        val insetX = EDGE_FUZZ_FACTOR * viewWidth
        val insetY = EDGE_FUZZ_FACTOR * viewHeight

        fun mockEvent(x: Float, y: Float): String {
            return getInsetCoordinates(
                    x, y,
                    viewPosition,
                    insetX, insetY,
                    viewWidth, viewHeight).joinToString()
        }

        // Insets should be added
        assertEquals(
                floatArrayOf(viewPosition[0] + insetX,
                             viewPosition[1] + insetY).joinToString(),
                mockEvent(0F, 0F))

        // Insets should be subtracted
        assertEquals(
                floatArrayOf(viewPosition[0] + viewWidth - insetX,
                             viewPosition[1] + viewHeight - insetY).joinToString(),
                mockEvent(1F, 1F))

        // Insets should be ignored
        assertEquals(
                floatArrayOf(viewPosition[0] + (viewWidth / 2),
                             viewPosition[1] + (viewHeight / 2)).joinToString(),
                mockEvent(.5F, .5F)
        )
    }
}