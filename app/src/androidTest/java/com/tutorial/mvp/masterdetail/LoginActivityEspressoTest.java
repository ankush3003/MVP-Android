package com.tutorial.mvp.masterdetail;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tutorial.mvp.masterdetail.constants.AppConstants;
import com.tutorial.mvp.masterdetail.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ankush3003 on 28/04/18.
 *
 * Test class for Validating credentials
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityEspressoTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class, true, true);

    @Test
    public void testCredentials() {
        Activity activity = loginActivityActivityTestRule.getActivity();

        // invalid credentials
        ViewInteraction usernameET = onView(withId(R.id.username));
        ViewInteraction passwordET = onView(withId(R.id.password));
        ViewInteraction signInBTN = onView(withId(R.id.btn_sign_in));

        usernameET.perform(typeText("random text"));
        passwordET.perform(typeText("password"));
        Espresso.closeSoftKeyboard();

        // verify login with invalid credentials
        signInBTN.perform(click());
        passwordET.check(ViewAssertions.matches(hasErrorText(activity.getString(R.string.error_incorrect_password))));


        // empty credentials
        usernameET.perform(clearText());
        passwordET.perform(clearText());
        signInBTN.perform(click());
        passwordET.check(ViewAssertions.matches(hasErrorText(activity.getString(R.string.error_field_required))));


        // Valid credentials
        usernameET.perform(typeText(AppConstants.DUMMY_CREDENTIALS[0]));
        passwordET.perform(typeText(AppConstants.DUMMY_CREDENTIALS[1]));
        Espresso.closeSoftKeyboard();
        signInBTN.perform(click());
        // Verify is Master activity is displayed
        onView(withText(activity.getString(R.string.master_activity_title))).check(ViewAssertions.matches(isDisplayed()));
    }
}
