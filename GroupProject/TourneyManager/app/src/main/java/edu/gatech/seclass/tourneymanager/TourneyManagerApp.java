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
        super.onCreate();
        appMode=null;
        tourneyRunning=false;
        pref = getSharedPreferences("TourneyManagerPref", Context.MODE_PRIVATE);
        edit = pref.edit();
        tourneyRunning = pref.getBoolean("tourneyRunning",tourneyRunning);
        edit.putBoolean("tourneyRunning",tourneyRunning);
        edit.commit();
        prefChanged=false;
    }
    public void setAppMode(AppMode m) { appMode = m; }
    public AppMode getappMode() { return appMode; }
    public boolean isTourneyRunning()
    {
        if(prefChanged)
        {
            prefChanged=false;
            tourneyRunning = false;
            tourneyRunning = pref.getBoolean("tourneyRunning", tourneyRunning);
        }
        return tourneyRunning;
    }
    public void setTourneyRunning(boolean r)
    {
        tourneyRunning=r;
        edit.putBoolean("tourneyRunning",tourneyRunning);
        edit.commit();
        prefChanged = true;
    }

    public void setLastTourneyId(int id)
    {
        edit.putInt("lastTid",id);
        edit.commit();
    }

    public int getLastTourneyId()
    {
        return pref.getInt("lastTid",0);
    }
    public void setLastMatchId(int id)
    {
        edit.putInt("lastMid",id);
        edit.commit();
    }

    public int getLastMatchId()
    {
        return pref.getInt("lastMid",0);
    }
    public void setLastRoundId(int id)
    {
        edit.putInt("lastRid",id);
        edit.commit();
    }

    public int getLastRoundId()
    {
        return pref.getInt("lastRid",0);
    }

    //Private Memebrs
    private AppMode appMode;
    private boolean tourneyRunning;
    private boolean prefChanged;
    private SharedPreferences pref;
    private  SharedPreferences.Editor edit;
}
