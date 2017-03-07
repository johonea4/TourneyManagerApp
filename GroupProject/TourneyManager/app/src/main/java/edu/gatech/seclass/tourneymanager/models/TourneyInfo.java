package edu.gatech.seclass.tourneymanager.models;

import java.util.Date;
import java.util.List;

/**
 * Created by IndikaP on 3/2/17.
 */

public class TourneyInfo
{
    int housePercent;
    int entryPrice;
    List<String> userNames;
    Date startDate;
    Date endDate;
    int numberOfEntrants;
    int firstPlacePrize;
    int secondPlacePrize;
    int ThirdPlacePrize;
    int houseCut;

    public void setCalculatedValues()
    {
        int potVal = numberOfEntrants*entryPrice;
        int cutVal = (int)((double)potVal * ((double)(housePercent)/100.0));
        int fVal = (int)((double)(potVal-cutVal)*0.5);
        int sVal = (int)((double)(potVal-cutVal)*0.3);
        int tVal = (int)((double)(potVal-cutVal)*0.2);

        houseCut = cutVal;
        firstPlacePrize = fVal;
        secondPlacePrize = sVal;
        ThirdPlacePrize = tVal;
    }
    public int getHousePercent() {
        return housePercent;
    }

    public void setHousePercent(int housePercent) {
        this.housePercent = housePercent;
    }

    public int getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(int entryPrice) {
        this.entryPrice = entryPrice;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfEntrants() {
        return numberOfEntrants;
    }

    public void setNumberOfEntrants(int numberOfEntrants) {
        this.numberOfEntrants = numberOfEntrants;
    }

    public int getFirstPlacePrize() {
        return firstPlacePrize;
    }

    public int getSecondPlacePrize() {
        return secondPlacePrize;
    }

    public int getThirdPlacePrize() {
        return ThirdPlacePrize;
    }

    public int getHouseCut() {
        return houseCut;
    }

}
