@file:JvmName("TestSuite")

package org.beatonma.lib.testing

import org.beatonma.lib.testing.espresso.SwipeTest
import org.beatonma.lib.testing.kotlin.extensions.ReflectionTest
import org.beatonma.lib.testing.kotlin.extensions.assertions.AnyTest
import org.beatonma.lib.testing.kotlin.extensions.assertions.ArraysTest
import org.beatonma.lib.testing.kotlin.extensions.assertions.MetaTest
import org.beatonma.lib.testing.kotlin.extensions.assertions.NumbersTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * A collection of tests for the lib.testing package
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
        AnyTest::class,
        ArraysTest::class,
        MetaTest::class,
        NumbersTest::class,
        ReflectionTest::class,
        SwipeTest::class)
class PackageSmallTests
