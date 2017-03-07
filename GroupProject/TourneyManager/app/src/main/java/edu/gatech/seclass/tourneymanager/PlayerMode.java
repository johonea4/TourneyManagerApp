package edu.gatech.seclass.tourneymanager;

/**
 * Created by johon on 2/25/2017.
 */

public class PlayerMode extends AppMode
{
    PlayerMode(TourneyManagerApp app)
    {
        super(mode_t.PLAYER);
        m_app = app;
    }

    private TourneyManagerApp m_app;
}
