package com.ajithvgiri.nasa


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val imageView = onView(
            allOf(
                withId(R.id.imageView),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withId(R.id.recyclerView),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.imageView),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withId(R.id.recyclerView),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
            allOf(
                withId(R.id.imageView),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withId(R.id.recyclerView),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))

        val frameLayout = onView(
            allOf(
                withId(R.id.container),
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView),
                        childAtPosition(
                            withId(R.id.frameLayoutFragmentPhotos),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
