package edu.gatech.seclass.tourneymanager.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IndikaP on 2/28/17.
 */

public class Round
{
    private int Id;
    //private int tournamentId;
    private ArrayList<String> winners;
    private ArrayList<Match> matches;
    private boolean running;
    private boolean finished;

    public void setId(int Id)
    {
        this.Id = Id;
    }

    public int getId()
    {
        return Id;
    }

//    public int getTournamentId() {
//        return tournamentId;
//    }
//
//    public void setTournamentId(int tournamentId) {
//        this.tournamentId = tournamentId;
//    }

    public void setWinners(ArrayList<String> winners) {
        this.winners = winners;
    }

    public List<String> getWinners() {
        return winners;
    }

    private void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
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

    public void createMatches(int matchCount) {

        matches = new ArrayList<Match>();
        for(int i=0; i<matchCount; i++)
        {
            Match match = new Match();
            match.setId(i+1);
            //match.setRoundId(Id);
            //match.setTournamentId(tournamentId);
            matches.add(match);
        }
    }
}