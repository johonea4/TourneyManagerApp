package edu.gatech.seclass.tourneymanager.Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.content.Context;

import edu.gatech.seclass.tourneymanager.db.MatchDBHelper;
import edu.gatech.seclass.tourneymanager.db.PlayerDBHelper;
import edu.gatech.seclass.tourneymanager.db.TournamentDBHelper;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Round;
import edu.gatech.seclass.tourneymanager.models.Tournament;

/**
 * Created by IndikaP on 3/2/17.
 */

    public class TourneyManagerDao
    {
        public static ArrayList<String> GetPlayerNames(Context context) {
            PlayerDBHelper playerDBHelper = new PlayerDBHelper(context);
            ArrayList<Player> players = playerDBHelper.getAllPlayers();
            ArrayList<String> playerNames = new ArrayList<String>();

            for(Player p : players) {
                playerNames.add(p.getUserName());
            }

            return playerNames;
        }

        public static Player GetPlayerByUsername(String userName, Context context) {
            PlayerDBHelper playerDBHelper = new PlayerDBHelper(context);
            ArrayList<Player> players = playerDBHelper.getAllPlayers();
            Iterator<Player> it = players.iterator();
            while (it.hasNext()) {
                Player p = it.next();
                String uName = p.getUserName();
                if (uName.compareTo(userName) == 0) {
                    return p;
                }
            }
            return null;
        }

        public List<Tournament> GetTournaments() {
            //TODO
            return null;
        }

        public Tournament GetActiveTournament() {
            //TODO
            return null;
        }

        public static void savePlayer(Player player, Context context)
        {
            PlayerDBHelper playerDBHelper = new PlayerDBHelper(context);
            playerDBHelper.addPlayer(player);
        }

        public static void UpdatePlayer(Player p, Context context)
        {
            PlayerDBHelper playerDBHelper = new PlayerDBHelper(context);
            playerDBHelper.updatePlayer(p);
        }

        public static void saveTournament(Tournament tourn, Context context)
        {
            TournamentDBHelper tournamentDBHelper = new TournamentDBHelper(context);
            tournamentDBHelper.addTournament(tourn);
            //TODO
        }

        public static void saveRounds(List<Round> rounds, Context context)
        {
            MatchDBHelper matchDBHelper = new MatchDBHelper(context);

            for(Round round:rounds) {
                List<Match> matches = round.getMatches();
                for(Match match: matches){
                    matchDBHelper.addMatch(match);
                }
            }
        }
}
