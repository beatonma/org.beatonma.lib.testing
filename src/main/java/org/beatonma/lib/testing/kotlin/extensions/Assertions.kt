package org.beatonma.lib.testing.kotlin.extensions

import android.graphics.Rect
import android.graphics.RectF
import org.junit.Assert


/**
 * Apply junit assertEquals() to ensure the sides of the receiver match the given values
 */
fun Rect.assertEquals(left: Int, top: Int, right: Int, bottom: Int) {
    Assert.assertEquals(left, this.left)
    Assert.assertEquals(top, this.top)
    Assert.assertEquals(right, this.right)
    Assert.assertEquals(bottom, this.bottom)
}

/**
 * Assert that at least one given value is different than the receiver values
 */
fun Rect.assertNotEquals(left: Int, top: Int, right: Int, bottom: Int) {
    Assert.assertFalse(
            left == this.left
                    && top == this.top
                    && right == this.right
                    && bottom == this.bottom)
}


/**
 * Apply junit assertEquals() to ensure the sides of the receiver match the given values
 */
fun RectF.assertEquals(left: Float, top: Float, right: Float, bottom: Float) {
    Assert.assertEquals(left, this.left)
    Assert.assertEquals(top, this.top)
    Assert.assertEquals(right, this.right)
    Assert.assertEquals(bottom, this.bottom)
}

/**
 * Assert that at least one given value is different than the receiver values
 */
fun RectF.assertNotEquals(left: Float, top: Float, right: Float, bottom: Float) {
    Assert.assertFalse(
            left == this.left
                    && top == this.top
                    && right == this.right
                    && bottom == this.bottom)
}