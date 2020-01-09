package com.github.kiolk.alphabet

import android.os.SystemClock
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import com.github.kiolk.alphabet.presentation.splash.SplashActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ShareScreenTest {

    private lateinit var device: UiDevice

    @get:Rule
    var activityRule: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)

    @Before
    fun setup() {
        device = UiDevice.getInstance(getInstrumentation())
    }

    @Test
    fun checkCorrectInitTimerState() {

        SystemClock.sleep(5000)

        onView(withId(R.id.iv_main_level_setting))
                .check(matches(isDisplayed()))
                .perform(click())


        onView(withText("Падзяліцца"))
                .check(matches(isDisplayed()))
                .perform(click())

    }
}