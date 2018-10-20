package com.mytaxi.android_demo;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SearchDriverTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    public ActivityTestRule<AuthenticationActivity> mActivityTestRule = new ActivityTestRule<>(AuthenticationActivity.class, true, false);

    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityRule.getActivity();
        IdlingRegistry.getInstance().register(ForIdlingRegistry.getResource());
    }

    @Test
    public void testLogin() throws InterruptedException {
        Log.d("@Test", "Performing login success test");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.edt_username))
                .perform(typeText("crazydog335"), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText("venture"), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());

        Thread.sleep(2000);
        onView(allOf(withId(R.id.textSearch), isDisplayed()))
                .perform(typeText("sa"), closeSoftKeyboard());
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());
        onView(withId(R.id.fab))
                .perform(click());
    }
}
