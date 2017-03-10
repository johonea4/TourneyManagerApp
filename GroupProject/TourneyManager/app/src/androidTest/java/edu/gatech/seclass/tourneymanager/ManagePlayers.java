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
 * Created by rugrani on 3/9/17.
 */

public class ManagePlayers {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(ManagePlayersActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("edu.gatech.seclass.tourneymanager", appContext.getPackageName());
    }

    @Test
    public void managerPlayers_createPlayer() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.createPlayerButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.addNewPlayerButton)).check(ViewAssertions.matches(ViewMatchers.withText("Create Player")));
    }

    @Test
    public void managerPlayers_createPlayer_uname() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.createPlayerButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.userNameField)).check(ViewAssertions.matches(ViewMatchers.withId((R.id.userNameField))));
    }

    @Test
    public void managerPlayers_createPlayer_name() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.createPlayerButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.nameField)).check(ViewAssertions.matches(ViewMatchers.withId((R.id.nameField))));
    }

    @Test
    public void managerPlayers_createPlayer_phone() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.createPlayerButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.phoneField)).check(ViewAssertions.matches(ViewMatchers.withId((R.id.phoneField))));
    }

    @Test
    public void managerPlayers_createPlayer_deck() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.createPlayerButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.deckChoiceSpinner)).check(ViewAssertions.matches(ViewMatchers.withId((R.id.deckChoiceSpinner))));
    }
}
