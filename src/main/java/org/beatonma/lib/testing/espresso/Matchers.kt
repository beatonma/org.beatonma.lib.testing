@file:JvmName("Matchers")

package org.beatonma.lib.testing.espresso


import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


/**
 * Ensure that the given {@link View} completely fills its parent
 */
class ViewSizeMatchesParentMatcher : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("View should have same bounds as its parent")
    }

    override fun matchesSafely(item: View?): Boolean {
        val parent = item?.parent as? View ?: return false
        with (item) {
            return left == 0 && top == 0 && width == parent.width && height == parent.height
        }
    }
}