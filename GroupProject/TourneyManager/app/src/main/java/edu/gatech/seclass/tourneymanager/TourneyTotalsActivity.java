package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Tournament;

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
        tourneyIds = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tourneyIds);
        tourneyList.setAdapter(adapter);
        setListCallBack();
    }

    class TotalsCompare implements Comparator<Tournament>
    {
        @Override
        public int compare(Tournament o1, Tournament o2) {
            int t1 = o1.isEndedEarly() ? 0 : o1.getInfo().getHouseCut();
            int t2 = o2.isEndedEarly() ? 0 : o2.getInfo().getHouseCut();

            return t1 < t2 ? 1 : t1==t2 ? 0 : -1;
        }
    }
    //TODO: Populate Info
    @Override
    protected void onStart(){
        super.onStart();
        m_mode = m_app.getappMode();

        List<Tournament> allTourneys = TourneyManagerDao.GetAllTournaments(TourneyTotalsActivity.this);
        Collections.sort(allTourneys,new TotalsCompare());
        int refundedVal = 0;
        int houseCutVal = 0;
        tourneyIds.clear();

        for(Tournament t : allTourneys){
            if(t.isEndedEarly()){
                refundedVal += t.getInfo().getEntryPrice() * t.getInfo().getNumberOfEntrants();
                String s = "Tournament #: " + String.valueOf(t.getId()) + " - Refunded";
                tourneyIds.add(s);
            }
            else if(!t.isEndedEarly() && !t.isRunning()){
                houseCutVal += t.getInfo().getHouseCut();
                String s = "Tournament #: " + String.valueOf(t.getId()) + " - $" + String.valueOf(t.getInfo().getHouseCut());
                tourneyIds.add(s);
            }
        }

        numTourneys.setText(Integer.toString(allTourneys.size()));
        refunded.setText("$"+Integer.toString(refundedVal)+".00");
        profit.setText("$"+Integer.toString(houseCutVal)+".00");
        adapter.notifyDataSetChanged();

    }

    public void setListCallBack()
    {
        tourneyList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view;
                if(!item.getText().toString().isEmpty())
                {
                    String s = item.getText().toString();
                    String num = s.substring(13,s.indexOf('-'));
                    num = num.replaceAll(" ","");
                    Intent i = new Intent(TourneyTotalsActivity.this,CreateTourneyActivity.class);
                    Bundle extras = new Bundle();
                    extras.putBoolean("viewTourneyInfo",true);
                    extras.putString("tourneyId",num);
                    i.putExtras(extras);
                    startActivity(i);
                }
            }
        });
    }


    //TODO: Connect Action of selecting total to display the CreateTourneyActivity
    //      This should tell the activity to display non creation info

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView tourneyList;
    private EditText numTourneys;
    private EditText profit;
    private EditText refunded;
    private ArrayList<String> tourneyIds;
    private ArrayAdapter<String> adapter;
}
