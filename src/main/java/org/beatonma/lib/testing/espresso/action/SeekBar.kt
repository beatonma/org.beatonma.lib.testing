@file:JvmName("SeekBar")

package org.beatonma.lib.testing.espresso.action

import android.view.View
import android.widget.SeekBar
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

fun setSeekBarProgress(progress: Int): ViewAction {
    return object: ViewAction {
        override fun getDescription(): String {
            return "Set the progress on a SeekBar view"
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isAssignableFrom(SeekBar::class.java)
        }

        override fun perform(uiController: UiController?, view: View?) {
            when (view) {
                is SeekBar -> {
                    view.progress = progress
                }
                else -> {
                    throw NoMatchingViewException.Builder().build()
                }
            }
        }
    }
}
