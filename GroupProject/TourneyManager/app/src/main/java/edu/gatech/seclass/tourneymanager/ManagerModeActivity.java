package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerModeActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_mode);

        m_app = (TourneyManagerApp)getApplication();
        appMode = m_app.getappMode();
        createButton = (Button)findViewById(R.id.createButton);
        manageButton = (Button)findViewById(R.id.managePlayersButton);
        playerTotalsButton = (Button)findViewById(R.id.playerTotalsButton);
        tourneyTotalsButton = (Button)findViewById(R.id.TourneyTotalsButton);
        roundsButton = (Button)findViewById(R.id.roundsButton);

    }

    public void setButtons()
    {
        if(m_app.isTourneyRunning())
        {
            createButton.setText("End Tournament");
            roundsButton.setVisibility(View.VISIBLE);
        }
        else
        {
            createButton.setText("Create Tournament");
            roundsButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setButtons();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        appMode = m_app.getappMode();
        setButtons();
    }

    public void CreateTourneyLauncher(View view)
    {
        if(m_app.isTourneyRunning())
        {
            m_app.setTourneyRunning(false);

            //method to find if the tournament ended early. Not all the rounds are completed.
            //method to check if all the rounds are being completed. call refund not.
            //update the tournament/ matches

            setButtons();
        }
        else
        {
            Intent createLauncher = new Intent(ManagerModeActivity.this, CreateTourneyActivity.class);
            createLauncher.putExtra("ManagerCreate",true);
            startActivity(createLauncher);
        }
    }
    public void ManagePlayersLauncher(View view)
    {
        Intent playersLauncher = new Intent(ManagerModeActivity.this,ManagePlayersActivity.class);
        startActivity(playersLauncher);
    }
    public void PlayerTotalsLauncher(View view)
    {
        Intent playersTotalsLauncher = new Intent(ManagerModeActivity.this,PlayerTotalsActivity.class);
        startActivity(playersTotalsLauncher);
    }
    public void TournamentTotalsLauncher(View view)
    {
        Intent tourneyTotalsLauncher = new Intent(ManagerModeActivity.this,TourneyTotalsActivity.class);
        startActivity(tourneyTotalsLauncher);
    }
    public void RoundsLauncher(View view)
    {
        Intent roundListLauncher = new Intent(ManagerModeActivity.this,RoundsListActivity.class);
        startActivity(roundListLauncher);
    }

    //Private members
    private TourneyManagerApp m_app;
    private AppMode appMode;
    private Button createButton;
    private Button manageButton;
    private Button playerTotalsButton;
    private Button tourneyTotalsButton;
    private Button roundsButton;
}
