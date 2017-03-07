package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeSelectActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);

        manager = new ManagerMode((TourneyManagerApp) getApplication());
        player = new PlayerMode((TourneyManagerApp) getApplication());
        managerButton = (Button)findViewById(R.id.manager_button);
        playerButton = (Button)findViewById(R.id.player_button);
    }
    public void startManagerMode(View view)
    {
        TourneyManagerApp a = (TourneyManagerApp) getApplication();
        a.setAppMode(manager);
        Intent launchManager = new Intent(ModeSelectActivity.this,ManagerModeActivity.class);
        startActivity(launchManager);

    }

    public void startPlayerMode(View view)
    {

    }

    private AppMode manager;
    private AppMode player;
    private Button managerButton;
    private Button playerButton;
}
