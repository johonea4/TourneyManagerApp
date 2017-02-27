package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PlayerTotalsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_totals);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        playerTotals = (ListView)findViewById(R.id.playerTotalsList);
    }

    //TODO: Populate List
    //TODO: Handle event from selecting list item to open PlayerInfoActivity

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView playerTotals;
}
