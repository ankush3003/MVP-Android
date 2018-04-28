package com.tutorial.mvp.masterdetail;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.matcher.UriMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.tutorial.mvp.masterdetail.master.MasterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by ankush3003 on 28/04/18.
 *
 * Test class for validating Master screen
 */

@RunWith(AndroidJUnit4.class)
public class MasterActivityEspressoTest {

    @Rule
    public IntentsTestRule<MasterActivity> activityTestRule =
            new IntentsTestRule<MasterActivity>(MasterActivity.class);

    @Test
    public void testMasterData() {
        // test item click
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(10));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Oreo Background Execution limits")),click()));

        // Verify if youtube intent was processed
        Intents.intended(allOf(
                IntentMatchers.hasAction(equalTo(Intent.ACTION_VIEW)),
                IntentMatchers.hasData(UriMatchers.hasHost(equalTo("www.youtube.com")))));
    }
}
