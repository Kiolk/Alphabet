package com.github.kiolk.alphabet

import android.os.SystemClock
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
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


        onView(withId(R.id.btn_share))
                .check(matches(isDisplayed()))
                .perform(click())
    }

    @Test
    fun checkBack(){
//        openShareScreen()
//
//        SystemClock.sleep(1000)
//
//        checkShareScreen()
//
//        onView(withId(R.id.btn_share_back))
//                .perform(click())
//
//        SystemClock.sleep(1000)
//
//        onView(withId(R.id.tv_share_title))
//                .check(doesNotExist())
//
//        onView(withId(R.id.btn_share))
//                .check(matches(isDisplayed()))
//                .perform(click())
//
//        SystemClock.sleep(1000)
//
//        checkShareScreen()
//
//        SystemClock.sleep(1000)
//
//        device.pressBack()
//
//        SystemClock.sleep(1000)
//
//        onView(withId(R.id.tv_share_title))
//                .check(doesNotExist())
//
//        onView(withId(R.id.btn_share))
//                .check(matches(isDisplayed()))
    }

    @Test
    fun checkShareLink(){
//        openShareScreen()
//
//        SystemClock.sleep(5000)
//
//        checkShareScreen()
//
//        SystemClock.sleep(5000)
//
//        onView(withId(R.id.btn_share_link))
//                .perform(click())

        //TODO add check for correct intent
    }

    private fun openShareScreen(){
        SystemClock.sleep(5000)

        onView(withId(R.id.iv_main_level_setting))
                .check(matches(isDisplayed()))
                .perform(click())


        onView(withText("Падзяліцца"))
                .check(matches(isDisplayed()))
                .perform(click())
    }

    private fun checkShareScreen(){
        onView(withText("Падзяліцца"))
                .check(matches(isDisplayed()))

        onView(withId(R.id.btn_share_back))
                .check(matches(isDisplayed()))

        onView((withId(R.id.btn_share)))
                .check(doesNotExist())

        onView((withId(R.id.btn_share_link)))
                .check(matches(isDisplayed()))

        onView(withId(R.id.iv_share_qr_code))
                .check(matches(isDisplayed()))
    }
}