package edu.gatech.seclass.tourneymanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Prize;

public class PrizeInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        tourneyid = (EditText)findViewById(R.id.tourneyIdField);
        tourneyid.setInputType(InputType.TYPE_NULL);
        place = (EditText)findViewById(R.id.placeField);
        place.setInputType(InputType.TYPE_NULL);
        moneyWon = (EditText)findViewById(R.id.moneyWonField);
        moneyWon.setInputType(InputType.TYPE_NULL);
    }

    @Override
    protected void onStart() {
        super.onStart();
        m_mode = m_app.getappMode();
        //TODO: Populate Fields;
        Intent i = getIntent();
        int tid = i.getIntExtra("tourneyId",-1);
        String uname = i.getStringExtra("uname");

        if(tid==-1 || uname==null)
        {
            Toast.makeText(PrizeInfoActivity.this,"An Error occurred when retrieving info.",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Player p = TourneyManagerDao.GetPlayerByUsername(uname,PrizeInfoActivity.this);
        if(p==null)
        {
            Toast.makeText(PrizeInfoActivity.this,"An Error occurred when retrieving info.",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        List<Prize> prizeList = p.getPrizes();
        Prize cPrize = null;
        for(Prize m_p : prizeList)
        {
            if(m_p.getTourneyId() == tid)
            {
                cPrize = m_p;
            }
        }
        if(cPrize==null)
        {
            Toast.makeText(PrizeInfoActivity.this,"An Error occurred when retrieving info.",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        this.tourneyid.setText(String.valueOf(cPrize.getTourneyId()));
        this.moneyWon.setText(String.valueOf(cPrize.getMoneyWon()));
        this.place.setText(String.valueOf(cPrize.getPlace()));
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private EditText tourneyid;
    private EditText moneyWon;
    private EditText place;
}
