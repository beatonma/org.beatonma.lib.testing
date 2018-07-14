@file:JvmName("FragmentTestActivity")

package org.beatonma.lib.testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class FragmentTestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
    }

    fun setFragment(fragment: Fragment, tag: String? = null) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, tag)
        }.commit()
    }
}