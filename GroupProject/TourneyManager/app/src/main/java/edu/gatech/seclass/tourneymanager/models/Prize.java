package edu.gatech.seclass.tourneymanager.models;

/**
 * Created by IndikaP on 2/28/17.
 */

public class Prize
{
    private int id;
    private String userName;
    private int place;
    private int tourneyId;
    private int MoneyWon;

    public Prize() {
    }

    public Prize(int id, String userName, int place, int tId, int moneyOwn) {
        this.id = id;
        this.userName = userName;
        this.place = place;
        this.tourneyId = tId;
        this.MoneyWon = moneyOwn;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

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
