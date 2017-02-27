package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.EditText;

public class PlayerInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();

        addPlayer = (Button)findViewById(R.id.addNewPlayerButton);
        prizeList = (ListView)findViewById(R.id.prizeList);
        name = (EditText)findViewById(R.id.nameField);
        username = (EditText)findViewById(R.id.userNameField);
        phone = (EditText)findViewById(R.id.phoneField);
        deckChoice = (Spinner)findViewById(R.id.deckChoiceSpinner);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        m_mode = m_app.getappMode();
        Intent intent = getIntent();

        if(intent.getBooleanExtra("doPlayerAdd",false))
        {
            addPlayer.setText("Create Player");
            prizeList.setVisibility(ListView.INVISIBLE);
            addPlayer.setVisibility(Button.VISIBLE);

        }
        else if(m_mode.getMode()== AppMode.mode_t.MANAGER)
        {
            addPlayer.setText("Update Player");
            prizeList.setVisibility(ListView.VISIBLE);
            addPlayer.setVisibility(Button.VISIBLE);

        }
        else
        {
            addPlayer.setVisibility(Button.INVISIBLE);
        }
    }

    //TODO: Populate Deck Choice Spinner
    //TODO: Populate PrizeList and set action to show prize info Activity

    public void onCreatePlayer(View view)
    {
        //TODO: Handle adding the player and checking for errors
        finish();
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private Button addPlayer;
    private ListView prizeList;
    private EditText name;
    private EditText username;
    private EditText phone;
    private Spinner deckChoice;
}
