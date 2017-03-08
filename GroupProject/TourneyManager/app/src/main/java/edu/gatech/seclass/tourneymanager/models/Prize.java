package edu.gatech.seclass.tourneymanager.models;

/**
 * Created by IndikaP on 2/28/17.
 */

public class Prize
{
    private String userName;
    private int place;
    private int tourneyId;
    private int MoneyWon;

    public int getPlace() {
        return place;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(int tourneyId) {
        this.tourneyId = tourneyId;
    }

    public int getMoneyWon() {
        return MoneyWon;
    }

    public void setMoneyWon(int moneyWon) {
        MoneyWon = moneyWon;
    }
}
