package edu.gatech.seclass.tourneymanager;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class PrizeInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        tourneyid = (EditText)findViewById(R.id.tourneyIdField);
        place = (EditText)findViewById(R.id.placeField);
        moneyWon = (EditText)findViewById(R.id.moneyWonField);
    }

    @Override
    protected void onStart() {
        super.onStart();
        m_mode = m_app.getappMode();
        //TODO: Populate Fields;
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private EditText tourneyid;
    private EditText moneyWon;
    private EditText place;
}
