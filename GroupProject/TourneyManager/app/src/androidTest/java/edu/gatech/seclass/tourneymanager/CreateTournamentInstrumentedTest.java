package edu.gatech.seclass.tourneymanager;

import android.content.Context;
import android.content.Intent;
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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateTournamentInstrumentedTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(ManagerModeActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("edu.gatech.seclass.tourneymanager", appContext.getPackageName());
    }

    @Test
    public void createTour_startTournament() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.tourneyStartButton)).check(ViewAssertions.matches(ViewMatchers.withText("Start Tournament")));
    }

    @Test
    public void createTour_tournamentId() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.textView2)).check(ViewAssertions.matches(ViewMatchers.withText("Tournament ID:")));
    }

    @Test
    public void createTour_entreeFee() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.textView4)).check(ViewAssertions.matches(ViewMatchers.withText("Entry Fee:")));
    }

    @Test
    public void createTour_housePercent() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.textView5)).check(ViewAssertions.matches(ViewMatchers.withText("House Percent:")));
    }

    @Test
    public void createTour_playerList() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.playerList)).check(ViewAssertions.matches(ViewMatchers.withId(R.id.playerList)));
    }
}
