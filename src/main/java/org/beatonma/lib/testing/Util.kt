@file:JvmName("Util")

package org.beatonma.lib.testing

import android.os.Parcel
import android.os.Parcelable
import org.junit.Assert.assertFalse

/**
 * [Parcelize][Parcelable] the given object and return a new instance by unpacking the parcel
 */
@Suppress("UNCHECKED_CAST")
fun <T : Parcelable> T.createCopyViaParcel(creatorName: String = "CREATOR"): T {
    val parcel = Parcel.obtain()
    writeToParcel(parcel, this.describeContents())
    parcel.setDataPosition(0)

    // Get CREATOR from static val
    val creator = this::class.java.declaredFields.find {
        it.name == creatorName
    }?.get(null) as Parcelable.Creator<T>

    return creator.createFromParcel(parcel).apply {
        assertFalse(this@apply === this@createCopyViaParcel)
    }
}
