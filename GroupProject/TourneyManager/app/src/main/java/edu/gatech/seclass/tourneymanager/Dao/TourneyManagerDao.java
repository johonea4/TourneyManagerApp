package edu.gatech.seclass.tourneymanager.Dao;

import java.util.ArrayList;
import java.util.HashMap;
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
import edu.gatech.seclass.tourneymanager.models.TourneyInfo;

/**
 * Created by IndikaP on 3/2/17.
 */

    public class TourneyManagerDao
    {
        public static ArrayList<String> GetPlayerNames(Context context)
        {
            PlayerDBHelper playerDBHelper = new PlayerDBHelper(context);
            ArrayList<Player> players = playerDBHelper.getAllPlayers();
            ArrayList<String> playerNames = new ArrayList<String>();

            for(Player p : players) {
                playerNames.add(p.getUserName());
            }

            return playerNames;
        }

        public static Player GetPlayerByUsername(String userName, Context context)
        {
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

        public static List<Tournament> GetAllTournaments(Context context)
        {
            TournamentDBHelper tournamentDBHelper = new TournamentDBHelper(context);
            return tournamentDBHelper.getAllTournament();
        }

        public static Tournament GetActiveTournament(Context context) {
            TournamentDBHelper tournamentDBHelper = new TournamentDBHelper(context);
            List<Tournament> tournaments = tournamentDBHelper.getAllTournament();
            Tournament activeTournament = null;

            for(Tournament tournament : tournaments){
                if(tournament.isRunning()){
                    activeTournament = tournament;
                }
            }
            return activeTournament;
        }

        public static void saveTournament(Tournament t, Context context)
        {
            TournamentDBHelper tournamentDBHelper = new TournamentDBHelper(context);
            tournamentDBHelper.addTournament(t);
        }

        public static void updateTournament(Tournament t, Context context)
        {
            TournamentDBHelper tournamentDBHelper = new TournamentDBHelper(context);
            tournamentDBHelper.updateTournament(t);
        }

        public static void saveRounds(List<Round> rounds, Context context)
        {
            MatchDBHelper matchDBHelper = new MatchDBHelper(context);

            for(Round round :rounds) {
                List<Match> matches = round.getMatches();
                for(Match match: matches){
                    matchDBHelper.addMatch(match);
                }
            }
        }

        public static Round GetRound(int tid, int id, Context context)
        {
            List<Match> matches = TourneyManagerDao.GetMatchesByRoundId(id,context);
            if(matches.size()<=0) return null;
            Round r = new Round(tid);
            Round.RoundState state = TourneyManagerDao.GetRoundState(id,context);

            if(state== Round.RoundState.NOT_STARTED) { r.setFinished(false); r.setRunning(false); }
            else if(state == Round.RoundState.RUNNING){ r.setFinished(false); r.setRunning(true); }
            else if(state==Round.RoundState.FINISHED) { r.setFinished(true); r.setRunning(false); }

            r.setMatches(matches);

            List<String> winners = new ArrayList<String>();
            for(Match m : matches)
            {
                if(!m.getWinners().isEmpty())
                    winners.add(m.getWinners());
            }
            r.setWinners(winners);

            return r;
        }

        public static HashMap<Integer,ArrayList<Match> > GetMatchesByTourneyId(int tourneyId, Context context)
        {
            MatchDBHelper matchDBHelper = new MatchDBHelper(context);
            List<Match> matches = matchDBHelper.getAllMatches();
            HashMap<Integer,ArrayList<Match> > matchesByTourneyId = new HashMap<Integer, ArrayList<Match> >();

            for(Match m : matches){
                if(tourneyId == m.getTournamentId()){
                    if(!matchesByTourneyId.containsKey(m.getRoundId())) matchesByTourneyId.put(m.getRoundId(),new ArrayList<Match>());
                    matchesByTourneyId.get(m.getRoundId()).add(m);
                }
            }
            return matchesByTourneyId;
        }

        public static List<Match> GetMatchesByRoundId(int roundId, Context context)
        {
            MatchDBHelper matchDBHelper = new MatchDBHelper(context);
            List<Match> matches = matchDBHelper.getAllMatches();
            List<Match> matchesByRoundId = new ArrayList<Match>();

            for(Match match : matches){
                if(roundId == match.getRoundId()){
                    matchesByRoundId.add(match);
                }
            }
            return matchesByRoundId;
        }

        public static Round.RoundState GetRoundState(int roundId, Context context)
        {
            List<Match> matches = TourneyManagerDao.GetMatchesByRoundId(roundId,context);
            boolean running = false;
            boolean finished = false;

            for(Match m : matches)
            {
                running |= m.isRunning();
                finished |= m.isFinished();
            }
            if(!running && !finished) return Round.RoundState.NOT_STARTED;
            if(running) return Round.RoundState.RUNNING;
            if(!running && finished) return Round.RoundState.FINISHED;
            return Round.RoundState.NOT_STARTED;
        }

        public static Match GetMatchById(int matchId, Context context)
        {
            MatchDBHelper matchDBHelper = new MatchDBHelper(context);
            List<Match> matches = matchDBHelper.getAllMatches();
            Match matchById = null;

            for(Match match : matches){
                if(matchId == match.getId()){
                    matchById = match;
                }
            }
            return matchById;
        }

        public static void UpdateMatch(Match m, Context context)
        {
            MatchDBHelper matchDBHelper = new MatchDBHelper(context);
            matchDBHelper.updateMatch(m);
        }
    }
