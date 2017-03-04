package edu.gatech.seclass.tourneymanager;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Round;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.TourneyInfo;

/**
 * Created by johon on 2/25/2017.
 */

public class ManagerMode extends AppMode
{
    ManagerMode()
    {
        super(mode_t.MANAGER);
    }

    public boolean createTournament(ArrayList<String> players, int tourneyId, int cutVal, int fVal, int sVal, int tVal) {

        Tournament tournament = new Tournament();
        tournament.setId(tourneyId);
        tournament.setRunning(true);

        TourneyInfo tourneyInfo = new TourneyInfo();
        tourneyInfo.setHouseCut(cutVal);
        tourneyInfo.setFirstPlacePrize(fVal);
        tourneyInfo.setSecondPlacePrize(sVal);
        tourneyInfo.setThirdPlacePrize(tVal);

        tournament.setInfo(tourneyInfo);

        try {
            TourneyManagerDao.saveTournament(tournament);
        } catch (Exception e) {
            return false;
        }
        return createRounds();
    }

    private boolean createRounds(){

        int listOfRounds = 4;
        int matches = 8;

        ArrayList<Round> rounds = new ArrayList<Round>();

        for(int i=0; i<listOfRounds; i++){
            Round round = new Round();
            round.setId(i+1);
            //round.setTournamentId(Integer.parseInt(tourneyID.getText().toString()));
            rounds.add(round);
            round.createMatches(matches);
            matches = matches / 2;
        }

        try {
            TourneyManagerDao.saveRounds(rounds);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean createPlayer(String name, String username, int phone, String deckChoice) {
        Player player = new Player();
        player.setName(name);
        player.setUserName(username);
        player.setPhoneNumber(phone);
        player.setDeckChoice(deckChoice);

        try {
            TourneyManagerDao.savePlayer(player); //db call
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public boolean updatePlayer(String name, String username, int phone, String deckChoice) {
        Player player = TourneyManagerDao.GetPlayer(username);
        if(player==null) return false;
        player.setName(name);
        player.setUserName(username);
        player.setPhoneNumber(phone);
        player.setDeckChoice(deckChoice);

        try {
            TourneyManagerDao.savePlayer(player); //db call
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
