package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Player;

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
        adapter = ArrayAdapter.createFromResource(this, R.array.deckChoice_array, android.R.layout.simple_spinner_item);
        deckChoice.setAdapter(adapter);
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
            String p = getIntent().getStringExtra("playerUserName");
            Player po = TourneyManagerDao.GetPlayer(p);
            if(po!=null)
            {
                name.setText(po.getName());
                username.setText(po.getUserName());
                phone.setText(String.valueOf(po.getPhoneNumber()));
                deckChoice.setSelection(adapter.getPosition(po.getDeckChoice()));
            }
            else
                finish();

        }
        else
        {
            addPlayer.setVisibility(Button.INVISIBLE);
            String p = getIntent().getStringExtra("playerUserName");
            Player po = TourneyManagerDao.GetPlayer(p);
            if(po!=null)
            {
                name.setText(po.getName());
                username.setText(po.getUserName());
                phone.setText(po.getPhoneNumber());
                deckChoice.setSelection(adapter.getPosition(po.getDeckChoice()));
            }
            else
                finish();
        }


    }

    //TODO: Populate PrizeList and set action to show prize info Activity

    public void onCreatePlayer(View view)
    {
        //TODO: Handle adding the player and checking for errors

        boolean valid = validateInputs();
        String nameStr = name.getText().toString();
        String usernameStr = username.getText().toString();
        int phoneNumber = Integer.parseInt(phone.getText().toString());
        String deckChoiceStr = deckChoice.getSelectedItem().toString();

        if(valid){
            Intent intent = getIntent();
            if(!intent.getBooleanExtra("doPlayerAdd",false)) {
                if (!((ManagerMode) m_mode).createPlayer(nameStr, usernameStr, phoneNumber, deckChoiceStr)) {
                    Toast.makeText(PlayerInfoActivity.this, "Unable to create player", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else
            {
                if(!((ManagerMode)m_mode).updatePlayer(nameStr, usernameStr, phoneNumber, deckChoiceStr))
                {
                    Toast.makeText(PlayerInfoActivity.this, "Unable to update player", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            finish();
        }
    }

    private boolean validateInputs()
    {
        boolean valid = true;
        if(name.getText().toString() == null || name.getText().toString().isEmpty()){
            name.setError("Invalid Name");
            valid = false;
        }
        if(username.getText().toString() == null || name.getText().toString().isEmpty()){
            username.setError("Invalid User Name");
            valid = false;
        }
        try {
            Integer.parseInt(phone.getText().toString());
        } catch (NumberFormatException e) {
            phone.setError("Invalid Phone Number");
            valid = false;
        }
        return valid;
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private Button addPlayer;
    private ListView prizeList;
    private EditText name;
    private EditText username;
    private EditText phone;
    private Spinner deckChoice;
    ArrayAdapter<CharSequence> adapter;
}
