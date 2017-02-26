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
        setButtons();
    }

    @Override
    protected void onRestart()
    {
        setButtons();
    }

    public void CreateTourneyLauncher(View view)
    {
        if(m_app.isTourneyRunning())
        {
            setButtons();
        }
        else
        {
            Intent createLauncher = new Intent(ManagerModeActivity.this, CreateTourney.class);
            startActivity(createLauncher);
        }
    }
    public void ManagePlayersLauncher(View view)
    {

    }
    public void PlayerTotalsLauncher(View view)
    {

    }
    public void TournamentTotalsLauncher(View view)
    {

    }
    public void RoundsLauncher(View view)
    {

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
