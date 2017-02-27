package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

class Recalculator implements TextWatcher
{
    Recalculator(Activity a)
    {
        activity = a;
    }

    public void beforeTextChanged(CharSequence txt, int start, int count, int after){}
    public void afterTextChanged(Editable s) {}
    public void onTextChanged(CharSequence txt, int start, int count, int after){ ((CreateTourneyActivity)activity).recalculate(); }

    private Activity activity;
}

public class CreateTourneyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tourney);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        playerList = (ListView)findViewById(R.id.playerList);
        housePercent = (EditText) findViewById(R.id.housePercentField);
        playerFee = (EditText)findViewById(R.id.feeField);
        houseCut = (EditText)findViewById(R.id.houseCutField);
        firstPlace = (EditText)findViewById(R.id.firstField);
        secondPlace = (EditText)findViewById(R.id.secondField);
        thirdPlace = (EditText)findViewById(R.id.thirdField);
        tourneyID = (EditText)findViewById(R.id.tourneyIdField);
        startButton = (Button)findViewById(R.id.tourneyStartButton);
        addPlayer = (Button)findViewById(R.id.addPlayerButton);
        removePlayer = (Button)findViewById(R.id.removePlayerButton);


        listener = new Recalculator(this);

        housePercent.addTextChangedListener(listener);
        playerFee.addTextChangedListener(listener);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        m_mode = m_app.getappMode();
        Intent i = getIntent();
        if(i.getBooleanExtra("ManagerCreate",false))
        {
            addPlayer.setVisibility(View.VISIBLE);
            removePlayer.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.VISIBLE);
            playerList.clearChoices();
            playerFee.setEnabled(true);
            housePercent.setEnabled(true);
            tourneyID.setEnabled(true);
        }
        else
        {
            addPlayer.setVisibility(View.INVISIBLE);
            removePlayer.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            playerFee.setEnabled(false);
            housePercent.setEnabled(false);
            tourneyID.setEnabled(false);
        }
    }

    public void recalculate()
    {
        int errors = 0;
        String feeString = playerFee.getText().toString();
        String percentString = housePercent.getText().toString();
        int feeVal=0, countVal=0, percentVal=0;
        if(feeString==null || feeString.length()<=0 || (feeVal = Integer.parseInt(feeString))<=0)
        {
            playerFee.setError("Invalid Fee”");
            errors++;
        }
        countVal = playerList.getCount();
        if((countVal)<3)
        {
            errors++;
        }
        if(percentString==null || percentString.length()<=0 || (percentVal=Integer.parseInt(percentString))<0 || (percentVal)>100)
        {
            housePercent.setError("Invalid House Percentage");
            errors++;
        }
        if(errors>0)
        {
            houseCut.setText("");
            firstPlace.setText("");
            secondPlace.setText("");
            thirdPlace.setText("");
            return;
        }

        int potVal = countVal*feeVal;
        int cutValue = (int)((double)potVal * ((double)(percentVal)/100.0));
        int fVal = (int)((double)(potVal-cutValue)*0.5);
        int sVal = (int)((double)(potVal-cutValue)*0.3);
        int tVal = (int)((double)(potVal-cutValue)*0.2);

        houseCut.setText(String.valueOf(cutValue));
        firstPlace.setText(String.valueOf(fVal));
        secondPlace.setText(String.valueOf(sVal));
        thirdPlace.setText(String.valueOf(tVal));
    }

    public void onStartTournament(View view)
    {
        m_app.setTourneyRunning(true);
        finish();
    }

    //TODO: Launch dialog with text field to enter players username
    public void onAddPlayer(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateTourneyActivity.this);

    }
    //TODO: Remove player from list that is currently selected
    public void onRemovePlayer(View view)
    {

    }

    //Private Members
    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private ListView playerList;
    private EditText housePercent;
    private EditText playerFee;
    private EditText houseCut;
    private EditText firstPlace;
    private EditText secondPlace;
    private EditText thirdPlace;
    private EditText tourneyID;
    private Button startButton;
    private Button addPlayer;
    private Button removePlayer;
    private TextWatcher listener;
}
