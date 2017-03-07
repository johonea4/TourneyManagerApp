package edu.gatech.seclass.tourneymanager.models;

import java.util.List;

/**
 * Created by IndikaP on 2/28/17.
 */

public class Player
{
    private int id;
    private String name;
    private String userName;
    private String phoneNumber;
    private String deckChoice;
    private List<Prize> prizes;
    private int matchesWon;

    public Player() {
    }

    public Player(int id, String name, String userName, String phoneNumber, String deckChoice) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.deckChoice = deckChoice;
    }

    public Player(String name, String userName, String phoneNumber, String deckChoice) {
        this.name = name;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.deckChoice = deckChoice;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
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
