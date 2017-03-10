package edu.gatech.seclass.tourneymanager;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by rugrani on 3/7/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayerInfoInstrumentedTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(ModeSelectActivity.class);

    @Test
    public void startManagerMode_ManagerPlayers_createPlayer() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.manager_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.managePlayersButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.createPlayerButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.nameField)).perform(ViewActions.typeText("testName"));
        Espresso.onView(ViewMatchers.withId(R.id.phoneField)).perform(ViewActions.typeText("1234567890"));
        Espresso.onView(ViewMatchers.withId(R.id.userNameField)).perform(ViewActions.typeText("tname"));

       // Espresso.onView(ViewMatchers.withId(R.id.addNewPlayerButton)).perform(ViewActions.click());

    }

    @Test
    public void startManagerMode_ManagerPlayers_ViewPlayer() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.manager_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.managePlayersButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.playerListView)).check(ViewAssertions.matches(ViewMatchers.withId((R.id.playerListView))));

    }


}
