@file:JvmName("Matchers")

package org.beatonma.lib.testing.espresso

import android.view.View
import androidx.annotation.CallSuper
import org.beatonma.lib.testing.kotlin.extensions.assertions.fuzzyEquals
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

abstract class ViewMatcher: TypeSafeMatcher<View>() {
    val actualMessage = StringBuilder()

    @CallSuper
    override fun describeTo(description: Description?) {
        description?.appendText("Actual: $actualMessage")
    }
}

abstract class FuzzyViewMatcher(val fuzzPx: Int = 1) : ViewMatcher() {
    @CallSuper
    override fun describeTo(description: Description?) {
        description?.appendText(" (+/- ${fuzzPx}px) ")
        super.describeTo(description)
    }
}

/**
 * Ensure that the given [View] completely fills its parent
 */
class ViewSizeMatchesParent : ViewMatcher() {
    override fun describeTo(description: Description?) {
        description?.appendText("View should have same bounds as its parent")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        val parent = item?.parent as? View ?: return false
        with(item) {
            actualMessage.append("item: $left, $top, $width, $height, parent: ${parent.width}, ${parent.height}")
            return left == 0 && top == 0 && width == parent.width && height == parent.height
        }
    }
}

/**
 * Ensure that the given [View] is centered in its parent (+/- fuzz pixels)
 */
class ViewCenteredInParent(fuzzPx: Int = 1) : FuzzyViewMatcher(fuzzPx) {
    override fun describeTo(description: Description?) {
        description?.appendText("View should be centered in parent")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        val parent = item?.parent as? View ?: return false
        actualMessage.append("Margins: ${item.left}, ${item.top}, ${parent.width - item.right} ")
        actualMessage.append(", ${parent.height - item.bottom}")
        return with(item) {
            left.fuzzyEquals(parent.width - right, fuzz = fuzzPx)
                    && top.fuzzyEquals(parent.height - bottom, fuzz = fuzzPx)
        }
    }
}

/**
 * Ensure that the given [View] has the expected height (in dp) (+/- fuzz dp)
 */
class ViewHeightIs(private val expectedHeightPx: Int, fuzzPx: Int = 1) : FuzzyViewMatcher(fuzzPx) {

    override fun describeTo(description: Description?) {
        description?.appendText("View height should be ${expectedHeightPx}px")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        val actualHeightPx = item?.measuredHeight ?: 0
        actualMessage.append("${actualHeightPx}px")
        return actualHeightPx.fuzzyEquals(expectedHeightPx, fuzz = fuzzPx)
    }
}

/**
 * Ensure that the given [View] has at least the expected height (in dp) (+/- fuzz dp)
 */
class ViewHeightAtLeast(private val expectedHeightPx: Int) : ViewMatcher() {

    override fun describeTo(description: Description?) {
        description?.appendText("View height should be AT LEAST ${expectedHeightPx}px")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        actualMessage.append("${item?.height ?: 0}")
        return item?.let { it.height >= expectedHeightPx } ?: false
    }
}

/**
 * Ensure that the given [View] has at most the expected height (in dp) (+/- fuzz dp)
 */
class ViewHeightAtMost(private val expectedHeightPx: Int) : ViewMatcher() {
    override fun describeTo(description: Description?) {
        description?.appendText("View height should be AT MOST ${expectedHeightPx}px")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        actualMessage.append("${item?.height ?: 0}")
        return item?.let { it.measuredHeight <= expectedHeightPx } ?: true
    }
}

/**
 * Ensure that the given [View] has the expected width (in dp) (+/- fuzz dp)
 */
class ViewWidthIs(private val expectedWidthPx: Int, fuzzPx: Int = 1) : FuzzyViewMatcher(fuzzPx) {
    override fun describeTo(description: Description?) {
        description?.appendText("View width should be ${expectedWidthPx}px")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        val actualWidthPx = item?.measuredWidth ?: 0
        actualMessage.append("${actualWidthPx}px")
        return expectedWidthPx.fuzzyEquals(actualWidthPx, fuzz = fuzzPx)
    }
}

/**
 * Ensure that the given [View] has at least the expected width (in dp) (+/- fuzz dp)
 */
class ViewWidthAtLeast(private val expectedWidthPx: Int) : ViewMatcher() {
    override fun describeTo(description: Description?) {
        description?.appendText("View width should be AT LEAST ${expectedWidthPx}px")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        actualMessage.append("${item?.width ?: 0}")
        return item?.let { it.measuredWidth >= expectedWidthPx } ?: false
    }
}

/**
 * Ensure that the given [View] has at most the expected width (in dp) (+/- fuzz dp)
 */
class ViewWidthAtMost(private val expectedWidthPx: Int) : ViewMatcher() {
    override fun describeTo(description: Description?) {
        description?.appendText("View width should be AT MOST ${expectedWidthPx}px")
        super.describeTo(description)
    }

    override fun matchesSafely(item: View?): Boolean {
        actualMessage.append("${item?.width ?: 0}")
        return item?.let { it.measuredWidth <= expectedWidthPx } ?: true
    }
}
