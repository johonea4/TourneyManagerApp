package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import edu.gatech.seclass.tourneymanager.db.Player;
import edu.gatech.seclass.tourneymanager.db.PlayerDBHelper;

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
        Player player = new Player();
        player.setUserName("something from the screen");
        player.setName("whatever");

        PlayerDBHelper helper = new PlayerDBHelper(this);
        helper.addPlayer(player);

        List<Player> playerList = helper.getAllPlayers();

    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView playerList;
    private Button createPlayer;
}
