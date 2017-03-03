package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Round;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.TourneyInfo;

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
        tournament = new Tournament();
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
        errors = 0;
        String tournamentId = tourneyID.getText().toString();
        String feeString = playerFee.getText().toString();
        String percentString = housePercent.getText().toString();
        int feeVal=0, countVal=0, percentVal=0;
        if(tournamentId == null || tournamentId.isEmpty())
        {
            tourneyID.setError("Invalid Tournament Id”");
            errors++;
        }
        if(feeString==null || feeString.isEmpty() || (feeVal = Integer.parseInt(feeString))<=0)
        {
            playerFee.setError("Invalid Fee”");
            errors++;
        }
        countVal = playerList.getCount();
        if((countVal)<3)
        {
            //errors++;
        }
        if(percentString==null || percentString.isEmpty() || (percentVal=Integer.parseInt(percentString))<0 || (percentVal)>100)
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
        cutVal = (int)((double)potVal * ((double)(percentVal)/100.0));
        fVal = (int)((double)(potVal-cutVal)*0.5);
        sVal = (int)((double)(potVal-cutVal)*0.3);
        tVal = (int)((double)(potVal-cutVal)*0.2);

        houseCut.setText(String.valueOf(cutVal));
        firstPlace.setText(String.valueOf(fVal));
        secondPlace.setText(String.valueOf(sVal));
        thirdPlace.setText(String.valueOf(tVal));
    }

    public void onStartTournament(View view)
    {
        m_app.setTourneyRunning(true);
        recalculate(); //Recheck. not necessarily required

        if(errors==0)
        {
            createTournament();
            createRounds();

            try {
                TourneyManagerDao.saveTournament(tournament);
                TourneyManagerDao.saveRounds(rounds);
            } catch (Exception e) {
                //TODO - throw an error popup
            }
            finish();
        }
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

    private void createTournament() {

        tournament.setId(Integer.parseInt(tourneyID.getText().toString()));
        tournament.setRunning(true);

        tourneyInfo = new TourneyInfo();
        tourneyInfo.setHouseCut(cutVal);
        tourneyInfo.setFirstPlacePrize(fVal);
        tourneyInfo.setSecondPlacePrize(sVal);
        tourneyInfo.setThirdPlacePrize(tVal);

        tournament.setInfo(tourneyInfo);
    }

    private void createRounds(){

        int listOfRounds = 4;
        int matches = 8;

        rounds = new ArrayList<Round>();

        for(int i=0; i<listOfRounds; i++){
            Round round = new Round();
            round.setId(i+1);
            //round.setTournamentId(Integer.parseInt(tourneyID.getText().toString()));
            rounds.add(round);
            round.createMatches(matches);
            matches = matches / 2;
        }
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

    private Tournament tournament;
    private TourneyInfo tourneyInfo;
    private ArrayList<Round> rounds;

    int errors;
    int cutVal;
    int fVal;
    int sVal;
    int tVal;
}
