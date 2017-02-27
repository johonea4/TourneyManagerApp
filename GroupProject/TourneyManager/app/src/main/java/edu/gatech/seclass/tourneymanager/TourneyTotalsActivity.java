package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class TourneyTotalsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourney_totals);

        m_app=(TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        tourneyList = (ListView)findViewById(R.id.tournamentList);
        numTourneys = (EditText)findViewById(R.id.numTourneyField);
        profit = (EditText)findViewById(R.id.profitField);
        refunded = (EditText)findViewById(R.id.refundedField);
    }

    //TODO: Populate Info
    //TODO: Connect Action of selecting total to display the CreateTourneyActivity
    //      This should tell the activity to display non creation info

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView tourneyList;
    private EditText numTourneys;
    private EditText profit;
    private EditText refunded;
}
