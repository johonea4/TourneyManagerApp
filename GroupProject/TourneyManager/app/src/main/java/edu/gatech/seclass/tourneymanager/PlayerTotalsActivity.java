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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Player;

public class PlayerTotalsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_totals);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        playerTotals = (ListView)findViewById(R.id.playerTotalsList);
        totals = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(PlayerTotalsActivity.this,android.R.layout.simple_list_item_1,totals);
        playerTotals.setAdapter(adapter);
        playerTotals.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view;
                String s = item.getText().toString();
                String uname = s.substring(8,s.indexOf('-')-1);

                Intent i = new Intent(PlayerTotalsActivity.this,PlayerInfoActivity.class);
                i.putExtra("playerUserName",uname);
                startActivity(i);
            }
        });
    }

    class TotalsCompare implements Comparator<Player>
    {
        @Override
        public int compare(Player o1, Player o2) {
            int t1 = o1.GetPlayerTotals();
            int t2 = o2.GetPlayerTotals();
            if(t1==t2) return 0;
            if(t1<t2) return 1;
            if(t1>t2) return -11;
            return 1;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        totals.clear();
        List<String> playerList = TourneyManagerDao.GetPlayerNames(PlayerTotalsActivity.this);
        ArrayList<Player> players = new ArrayList<Player>();
        for(String uname : playerList)
        {
            Player tmp = TourneyManagerDao.GetPlayerByUsername(uname,PlayerTotalsActivity.this);
            if(tmp != null)
            {
                players.add(tmp);
            }
        }
        if(players.size()>0)
        {
            Collections.sort(players,new TotalsCompare());
            for(Player p : players)
            {
                String s = "Player: " + p.getUserName();
                s += " - $" + String.valueOf(p.GetPlayerTotals());
                totals.add(s);
            }
        }
        adapter.notifyDataSetChanged();
    }

    //TODO: Populate List
    //TODO: Handle event from selecting list item to open PlayerInfoActivity

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView playerTotals;
    private ArrayList<String> totals;
    private ArrayAdapter<String> adapter;
}
