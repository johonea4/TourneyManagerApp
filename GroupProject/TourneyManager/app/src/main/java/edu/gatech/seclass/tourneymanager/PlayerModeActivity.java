package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayerModeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_mode);

        m_app = (TourneyManagerApp)getApplication();
        appMode = m_app.getappMode();
    }

    public void setScreen()
    {
        if(m_app.isTourneyRunning())
        {
            Intent playersTotalsLauncher = new Intent(PlayerModeActivity.this,PlayerTotalsActivity.class);
            startActivity(playersTotalsLauncher);
        }
        else
        {

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setScreen();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        appMode = m_app.getappMode();
        setScreen();
    }

    public void PlayerTotalsLauncher(View view)
    {
        Intent playersTotalsLauncher = new Intent(PlayerModeActivity.this,PlayerTotalsActivity.class);
        startActivity(playersTotalsLauncher);
    }

    public void RoundsLauncher(View view)
    {
        Intent roundListLauncher = new Intent(PlayerModeActivity.this,RoundsListActivity.class);
        startActivity(roundListLauncher);
    }

    //Private members
    private TourneyManagerApp m_app;
    private AppMode appMode;

}
