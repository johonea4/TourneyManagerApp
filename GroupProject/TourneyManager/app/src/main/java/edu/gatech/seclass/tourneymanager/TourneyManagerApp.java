package edu.gatech.seclass.tourneymanager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by johon on 2/25/2017.
 */

public class TourneyManagerApp extends Application
{
    //Public Methods
    @Override
    public void onCreate()
    {
        appMode=null;
        tourneyRunning=false;
        SharedPreferences pref = getSharedPreferences("TourneyManagerPref", Context.MODE_PRIVATE);
        tourneyRunning = pref.getBoolean("tourneyRunning",tourneyRunning);
        pref.edit().putBoolean("tourneyRunning",tourneyRunning);
    }
    public void setAppMode(AppMode m) { appMode = m; }
    public AppMode getappMode() { return appMode; }
    public boolean isTourneyRunning() { return tourneyRunning; }

    //Private Memebrs
    private AppMode appMode;
    private boolean tourneyRunning;
}
