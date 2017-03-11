package edu.gatech.seclass.tourneymanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by rugrani on 3/10/17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateTournamentInstrumentedTest.class,
        ManagePlayersInstrumentedTest.class,
        ModeSelectActivityInstrumentedTest.class,
        PlayerInfoInstrumentedTest.class})
public class UnitTestSuite {
}
