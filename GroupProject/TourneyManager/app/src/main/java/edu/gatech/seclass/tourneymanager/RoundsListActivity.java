package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RoundsListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds_list);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        roundsList = (ListView)findViewById(R.id.roundsList);
    }

    //TODO: Populate Round List
    //TODO: When round is selected launch RoundInfoActivity

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView roundsList;
}
