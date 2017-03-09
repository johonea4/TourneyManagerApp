package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Prize;

public class PlayerInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();

        addPlayer = (Button)findViewById(R.id.addNewPlayerButton);
        rmPlayer = (Button)findViewById(R.id.removeButton);
        prizeList = (ListView)findViewById(R.id.prizeList);
        name = (EditText)findViewById(R.id.nameField);
        username = (EditText)findViewById(R.id.userNameField);
        phone = (EditText)findViewById(R.id.phoneField);
        deckChoice = (Spinner)findViewById(R.id.deckChoiceSpinner);
        adapter = ArrayAdapter.createFromResource(PlayerInfoActivity.this, R.array.deckChoice_array, android.R.layout.simple_spinner_item);
        prizes = new ArrayList<String>();
        prizeAdapter = new ArrayAdapter<String>(PlayerInfoActivity.this,android.R.layout.simple_list_item_1,prizes);
        deckChoice.setAdapter(adapter);
        prizeList.setAdapter(prizeAdapter);
        setPrizeListcb();
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
            rmPlayer.setVisibility(Button.INVISIBLE);
            name.setInputType(InputType.TYPE_CLASS_TEXT);
            username.setInputType(InputType.TYPE_CLASS_TEXT);
            phone.setInputType(InputType.TYPE_CLASS_PHONE);
            deckChoice.setEnabled(true);
        }
        else if(m_mode.getMode()== AppMode.mode_t.MANAGER)
        {
            addPlayer.setText("Update Player");
            prizeList.setVisibility(ListView.VISIBLE);
            addPlayer.setVisibility(Button.VISIBLE);
            rmPlayer.setVisibility(Button.VISIBLE);
            name.setInputType(InputType.TYPE_CLASS_TEXT);
            username.setInputType(InputType.TYPE_NULL);
            phone.setInputType(InputType.TYPE_CLASS_PHONE);
            deckChoice.setEnabled(true);
            String p = getIntent().getStringExtra("playerUserName");
            curPlayer = TourneyManagerDao.GetPlayerByUsername(p, PlayerInfoActivity.this);
            if(curPlayer!=null)
            {
                name.setText(curPlayer.getName());
                username.setText(curPlayer.getUserName());
                phone.setText(String.valueOf(curPlayer.getPhoneNumber()));
                deckChoice.setSelection(adapter.getPosition(curPlayer.getDeckChoice()));
                populatePrizes();
            }
            else
                finish();

        }
        else
        {
            addPlayer.setVisibility(Button.INVISIBLE);
            rmPlayer.setVisibility(Button.INVISIBLE);
            name.setInputType(InputType.TYPE_NULL);
            username.setInputType(InputType.TYPE_NULL);
            phone.setInputType(InputType.TYPE_NULL);
            deckChoice.setEnabled(false);
            String p = getIntent().getStringExtra("playerUserName");
            curPlayer = TourneyManagerDao.GetPlayerByUsername(p, PlayerInfoActivity.this);
            if(curPlayer!=null)
            {
                name.setText(curPlayer.getName());
                username.setText(curPlayer.getUserName());
                phone.setText(String.valueOf(curPlayer.getPhoneNumber()));
                deckChoice.setSelection(adapter.getPosition(curPlayer.getDeckChoice()));
                populatePrizes();
            }
            else
                finish();
        }


    }

    //TODO: Populate PrizeList and set action to show prize info Activity
    public void populatePrizes()
    {
        prizes.clear();
        List<Prize> pList = curPlayer.getPrizes();
        for(Prize p : pList)
        {
            String s = "Tournament #: ";
            s += String.valueOf(p.getTourneyId());
            s += " - $";
            s += String.valueOf(p.getMoneyWon());
            prizes.add(s);
        }
        prizeAdapter.notifyDataSetChanged();
    }
    public void setPrizeListcb()
    {
        prizeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view;
                String s = item.getText().toString();
                String num = s.substring(13,s.indexOf('-'));
                num = num.replaceAll(" ","");
                int n = Integer.parseInt(num);

                Intent i = new Intent(PlayerInfoActivity.this, PrizeInfoActivity.class);
                i.putExtra("tourneyId",n);
                i.putExtra("uname",curPlayer.getUserName());
                startActivity(i);
            }
        });
    }
    public void onCreatePlayer(View view)
    {
        //TODO: Handle adding the player and checking for errors

        boolean valid = validateInputs();
        String nameStr = name.getText().toString();
        String usernameStr = username.getText().toString();
        String phoneNumber = phone.getText().toString();
        String deckChoiceStr = deckChoice.getSelectedItem().toString();

        if(valid){
            Intent intent = getIntent();
            if(intent.getBooleanExtra("doPlayerAdd",false)) {
                if (!((ManagerMode) m_mode).createPlayer(nameStr, usernameStr, phoneNumber, deckChoiceStr, PlayerInfoActivity.this)) {
                    Toast.makeText(PlayerInfoActivity.this, "Unable to create player", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else
            {
                if(!((ManagerMode)m_mode).updatePlayer(nameStr, usernameStr, phoneNumber, deckChoiceStr, PlayerInfoActivity.this))
                {
                    Toast.makeText(PlayerInfoActivity.this, "Unable to update player", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            finish();
        }
    }

    public void onRemovePlayer(View view)
    {
        String p = getIntent().getStringExtra("playerUserName");
        TourneyManagerDao.RemovePlayer(p,PlayerInfoActivity.this);
        finish();
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
        if(phone.getText().toString() == null || phone.getText().toString().isEmpty()){
            phone.setError("Invalid Phone Number");
            valid = false;
        }
        return valid;
    }

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private Button addPlayer;
    private Button rmPlayer;
    private ListView prizeList;
    private EditText name;
    private EditText username;
    private EditText phone;
    private Spinner deckChoice;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<String> prizeAdapter;
    private ArrayList<String> prizes;
    private Player curPlayer;

}
