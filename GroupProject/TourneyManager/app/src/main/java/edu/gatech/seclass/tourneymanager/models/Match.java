package edu.gatech.seclass.tourneymanager.models;

/**
 * Created by IndikaP on 2/28/17.
 */

public class Match
{
    private int id;
     private int roundId;
    private int tournamentId;
    private String winner;
    private String player1;
    private String player2;
    private boolean running;
    private boolean finished;

    public Match() {
    }

    public Match(int id, String winner, String player1, String player2, boolean running, boolean finished, int roundId, int tournamentId) {
        this.id = id;
        this.winner = winner;
        this.player1 = player1;
        this.player2 = player2;
        this.running = running;
        this.finished = finished;
        this.roundId = roundId;
        this.tournamentId = tournamentId;
    }

    public void setId(int Id)
    {
        this.id = Id;
    }

    public int getId()
    {
        return id;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setWinners(String winner)
    {
        this.winner = winner;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setPlayer1(String player)
    {
        this.player1 = player;
    }

    public String getPlayer1()
    {
        return player1;
    }

    public void setPlayer2(String player)
    {
        this.player2 = player2;
    }

    public String getPlayer2()
    {
        return player2;
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
}