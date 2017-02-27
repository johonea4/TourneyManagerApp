package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class RoundInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        roundID = (EditText)findViewById(R.id.roundIdField);
        matchList = (ListView)findViewById(R.id.matchList);
    }

    //TODO: Populate List
    //TODO: When List Item is selected launch MatchInfoActivity

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private EditText roundID;
    private ListView matchList;
}
