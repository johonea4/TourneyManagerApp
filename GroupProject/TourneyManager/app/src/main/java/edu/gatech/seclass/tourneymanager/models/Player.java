package edu.gatech.seclass.tourneymanager.models;

import java.util.List;

/**
 * Created by IndikaP on 2/28/17.
 */

public class Player
{
    private String name;
    private String userName;
    private int phoneNumber;
    private String deckChoice;
    private List<Prize> prizes;
    private int matchesWon;

    public int GetPlayerTotals()
    {
        return 0;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setDeckChoice(String deckChoice) { this.deckChoice = deckChoice; }

    public String getDeckChoice()
    {
        return deckChoice;
    }

    public void setPrizes(List<Prize> prizes)
    {
        this.prizes = prizes;
    }

    public List<Prize> getPrizes()
    {
        return prizes;
    }
}
