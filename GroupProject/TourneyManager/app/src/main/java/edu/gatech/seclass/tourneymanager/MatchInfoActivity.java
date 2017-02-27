package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MatchInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        matchID = (EditText)findViewById(R.id.matchIdField);
        player1 = (EditText)findViewById(R.id.player1Field);
        player2 = (EditText)findViewById(R.id.player2Field);
        winner = (EditText)findViewById(R.id.winnerField);
        matchStart = (Button)findViewById(R.id.matchStartButton);
    }

    //TODO: Populate Fields
    //TODO: Set Button listener and record match state

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private EditText matchID;
    private EditText player1;
    private EditText player2;
    private EditText winner;
    private Button matchStart;
}
