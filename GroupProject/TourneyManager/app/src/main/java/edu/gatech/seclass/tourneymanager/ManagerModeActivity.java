package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ManagerModeActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_mode);

        appMode = ((TourneyManagerApp)getApplication()).getappMode();
    }


    //Private members
    AppMode appMode;

}
