package edu.gatech.seclass.tourneymanager.models;

import java.util.List;

/**
 * Created by IndikaP on 3/2/17.
 */

public class Tournament
{
    private int id;
    private boolean running;
    private boolean finished;
    private TourneyInfo info;
    private boolean endedEarly;
    private List<Round> rounds;


    public Tournament() {
    }

    public Tournament(int id, boolean running, boolean endedEarly) {
        this.id = id;
        this.running = running;
        this.endedEarly = endedEarly;
    }

    public void setId(int Id)
    {
        this.id = Id;
    }

    public int getId()
    {
        return id;
    }

    public void setInfo(TourneyInfo info)
    {
        this.info = info;
    }

    public TourneyInfo getInfo()
    {
        return info;
    }

    public void setEndedEarly(boolean endedEarly)
    {
        this.endedEarly = endedEarly;
    }

    public boolean isEndedEarly()
    {
        return endedEarly;
    }

    public void setRounds(List<Round> rounds)
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

    public boolean isRunning()
    {
        return running;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void Refund()
    {
        //TODO
    }

}
