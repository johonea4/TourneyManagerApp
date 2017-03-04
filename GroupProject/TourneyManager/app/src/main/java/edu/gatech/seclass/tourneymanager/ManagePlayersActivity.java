package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.db.PlayerDBHelper;
import edu.gatech.seclass.tourneymanager.models.Player;

public class ManagePlayersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_players);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        playerList = (ListView) findViewById(R.id.playerListView);
        createPlayer = (Button)findViewById(R.id.createPlayerButton);
        populatePlayers();
        setListCallBack();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        m_mode = m_app.getappMode();
        adapter.notifyDataSetChanged();
    }
    public void onCreatePlayer(View view)
    {
        Intent playerLauncher = new Intent(ManagePlayersActivity.this,PlayerInfoActivity.class);
        playerLauncher.putExtra("doPlayerAdd",true);
        startActivity(playerLauncher);
    }

    //TODO: Populate player list with stored players from Database
    public void populatePlayers()
    {
        players = TourneyManagerDao.GetPlayerNames(ManagePlayersActivity.this);

        adapter = adapter==null ? new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, players) : adapter;
        playerList.setAdapter(adapter);
    }
    public void setListCallBack()
    {
        playerList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view;
                if(!item.getText().toString().isEmpty())
                {
                    Intent i = new Intent(ManagePlayersActivity.this,PlayerInfoActivity.class);
                    i.putExtra("playerUserName",item.getText().toString());
                    startActivity(i);
                }
            }
        });
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView playerList;
    private Button createPlayer;
    ArrayAdapter adapter;
    ArrayList<String> players;
}
