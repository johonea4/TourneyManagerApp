package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        m_mode = m_app.getappMode();
        populatePlayers();
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
        //ArrayList<Player> players = TourneyManagerDao.GetPlayerNames();
        PlayerDBHelper playerDBHelper = new PlayerDBHelper(this);
        List<edu.gatech.seclass.tourneymanager.db.Player> players = playerDBHelper.getAllPlayers();
        ArrayList<String> playerNames = new ArrayList<String>();

        for(edu.gatech.seclass.tourneymanager.db.Player p : players) {
            playerNames.add(p.getUserName());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playerNames);
        playerList.setAdapter(adapter);
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView playerList;
    private Button createPlayer;
}
