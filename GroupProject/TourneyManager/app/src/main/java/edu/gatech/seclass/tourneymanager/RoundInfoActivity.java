package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Match;

public class RoundInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        roundID = (EditText)findViewById(R.id.roundIdField);
        roundID.setInputType(InputType.TYPE_NULL);
        matchList = (ListView)findViewById(R.id.matchList);
        matches = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(RoundInfoActivity.this, android.R.layout.simple_list_item_1, matches);
        matchList.setAdapter(adapter);
        setListCallback();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        id = i.getIntExtra("roundID",-1);
        if(id==-1) { finish(); return; }

        roundID.setText(String.valueOf(id));
        populateMatches();
    }
    public void populateMatches()
    {
        matches.clear();

        if(TourneyManagerDao.GetActiveTournament(RoundInfoActivity.this)!=null)
        {
            List<Match> matchArray = TourneyManagerDao.GetMatchesByRoundId(id,RoundInfoActivity.this);

                for(Match m : matchArray) {
                    String s = "";
                    s += "Match: " + String.valueOf(m.getId()) + " - Status: " + (m.isRunning() ? "Running" : ((m.isFinished() && !m.isRunning()) ? "Complete" : "Not Started"));
                    matches.add(s);
                }
        }
        adapter.notifyDataSetChanged();
    }
    public void setListCallback()
    {
        matchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view;
                String s = item.getText().toString();
                String num = s.substring(6,s.indexOf('-'));
                num = num.replaceAll(" ","");
                int n = Integer.parseInt(num);

                Intent i = new Intent(RoundInfoActivity.this,MatchInfoActivity.class);
                i.putExtra("matchID",n);
                startActivity(i);
            }
        });
    }
    //TODO: Populate List
    //TODO: When List Item is selected launch MatchInfoActivity

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private EditText roundID;
    private ListView matchList;
    private ArrayList<String> matches;
    private ArrayAdapter<String> adapter;
    private int id;
}
