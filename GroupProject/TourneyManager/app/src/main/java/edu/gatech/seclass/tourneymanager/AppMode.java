package edu.gatech.seclass.tourneymanager;

/**
 * Created by johon on 2/25/2017.
 */

public class AppMode
{
    //Public members/definitions
    AppMode(mode_t m)
    {
        mode=m;
    }
    public enum mode_t {MANAGER,PLAYER};
    public mode_t getMode() { return mode; }

    //Private members
    private mode_t mode;

}