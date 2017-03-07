package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Match;

public class RoundsListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds_list);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        roundsList = (ListView)findViewById(R.id.roundsList);
        rounds = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(RoundsListActivity.this, android.R.layout.simple_list_item_1, rounds);
        roundsList.setAdapter(adapter);
        setListCallback();
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateRounds();
    }

    public void populateRounds()
    {
        rounds.clear();
        List<Match> matches;

        if(TourneyManagerDao.GetActiveTournament(RoundsListActivity.this)!=null)
        {
            HashMap< Integer, ArrayList<Match> > matchMap;
            matchMap = TourneyManagerDao.GetMatchesByTourneyId(TourneyManagerDao.GetActiveTournament(RoundsListActivity.this).getId(),RoundsListActivity.this);

            Set<Integer> keys = matchMap.keySet();
            for(Integer i : keys)
            {
                boolean running = false;
                boolean finished = false;

                for(Match m : matchMap.get(i))
                {
                    running |= m.isRunning();
                    finished |= m.isFinished();
                }

                String s = "";
                s+= "Round: " + String.valueOf(i) + " - Status:" + (running ? "Running" : ((finished && !running) ? "Complete" : "Not Started"));
                rounds.add(s);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void setListCallback()
    {
        roundsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view;
                String s = item.getText().toString();
                String num = s.substring(6,s.indexOf('-'));
                num = num.replaceAll(" ","");
                int n = Integer.parseInt(num);

                Intent i = new Intent(RoundsListActivity.this,RoundInfoActivity.class);
                i.putExtra("roundID",n);
                startActivity(i);
            }
        });
    }
    //TODO: Populate Round List
    //TODO: When round is selected launch RoundInfoActivity

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView roundsList;
    private ArrayList<String> rounds;
    private ArrayAdapter<String> adapter;
}
