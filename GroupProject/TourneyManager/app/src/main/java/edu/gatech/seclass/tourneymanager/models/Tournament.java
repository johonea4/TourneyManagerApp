package edu.gatech.seclass.tourneymanager.models;

import java.util.List;

import edu.gatech.seclass.tourneymanager.models.TourneyInfo;

/**
 * Created by IndikaP on 3/2/17.
 */

public class Tournament
{
    private int Id;
    private boolean running;
    private boolean finished;
    private TourneyInfo info;
    private boolean endedEarly;
    private List<Round> rounds;


    public void setId(int Id)
    {
        this.Id = Id;
    }

    public int getId()
    {
        return Id;
    }

    public void setInfo(TourneyInfo info)
    {
        this.info = info;
    }

    public TourneyInfo getInfo()
    {
        return info;
    }

    private void setEndedEarly(boolean endedEarly)
    {
        this.endedEarly = endedEarly;
    }

    public boolean getEndedEarly()
    {
        return endedEarly;
    }

    private void setRounds(List<Round> rounds)
    {
        this.rounds = rounds;
    }

    public List<Round> getRounds()
    {
        return rounds;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public boolean getRunning()
    {
        return running;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public boolean getFinished()
    {
        return finished;
    }

    public void Refund()
    {
        //TODO
    }

}
