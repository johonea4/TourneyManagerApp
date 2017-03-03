package edu.gatech.seclass.tourneymanager.Dao;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;

import edu.gatech.seclass.tourneymanager.db.PlayerDBHelper;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Round;
import edu.gatech.seclass.tourneymanager.models.Tournament;

/**
 * Created by IndikaP on 3/2/17.
 */

    public class TourneyManagerDao
    {
        public static ArrayList<Player> players = new ArrayList<Player>();
        public static ArrayList<String> GetPlayerNames(Context context) {
            //TODO

        Player p1 = new Player();
        p1.setUserName("kodagi1");
        p1.setName("Indika Pa");
        p1.setPhoneNumber(2023222333);
        p1.setDeckChoice("Engineer");
        players.add(p1);

        Player p2 = new Player();
        p2.setUserName("2");
        p2.setName("2");
        p2.setPhoneNumber(2);
        p2.setDeckChoice("T");
        players.add(p2);

        Player p3 = new Player();
        p3.setUserName("3");
        p3.setName("3");
        p3.setPhoneNumber(3);
        p3.setDeckChoice("T");
        players.add(p3);

//        PlayerDBHelper playerDBHelper = new PlayerDBHelper(context);
//        ArrayList<Player> players = playerDBHelper.getAllPlayers();
        ArrayList<String> playerNames = new ArrayList<String>();

        for(Player p : players) {
            playerNames.add(p.getUserName());
        }

        return playerNames;
    }

    public List<Tournament> GetTournaments() {
        //TODO
        return null;
    }

    public Tournament GetActiveTournament() {
        //TODO
        return null;
    }

    public static void savePlayer(Player player)
    {
        players.add(player);
    }

    public static void saveTournament(Tournament tourn)
    {
        //TODO
    }

    public static void saveRounds(List<Round> rounds)
    {
        for (Round round:rounds) {
            List<Match> matches = round.getMatches();
            //TODO
        }
    }
}
