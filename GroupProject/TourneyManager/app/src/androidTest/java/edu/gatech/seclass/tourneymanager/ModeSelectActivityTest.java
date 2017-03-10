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
public class ModeSelectActivityTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(ModeSelectActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("edu.gatech.seclass.tourneymanager", appContext.getPackageName());
    }

    @Test
    public void startManagerMode_ManagerPlayers() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.manager_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.managePlayersButton)).check(ViewAssertions.matches(ViewMatchers.withText("Manage Players")));
    }

    @Test
    public void startManagerMode_CreateTournament() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Espresso.onView(ViewMatchers.withId(R.id.manager_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.createButton)).check(ViewAssertions.matches(ViewMatchers.withText("Create Tournament")));
    }

    @Test
    public void startManagerMode_ViewPlayerTotals() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.manager_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.playerTotalsButton)).check(ViewAssertions.matches(ViewMatchers.withText("View Player Totals")));
    }

    @Test
    public void startManagerMode_ViewTourTotals() throws Exception {
        // Context of the app under test.
        Espresso.onView(ViewMatchers.withId(R.id.manager_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.TourneyTotalsButton)).check(ViewAssertions.matches(ViewMatchers.withText("View Tournament Totals")));
    }

    @Test
    public void startPlayer_ViewPlayTotals() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.player_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.playerTotalsButton_player)).check(ViewAssertions.matches(ViewMatchers.withText("View Player Totals")));
    }

    /*@Test
    public void startPlayer_ViewRounds() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.player_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.roundsButton)).check(ViewAssertions.matches(ViewMatchers.withText("View Rounds")));
    }*/
}
