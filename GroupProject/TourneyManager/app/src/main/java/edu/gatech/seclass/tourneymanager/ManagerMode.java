package edu.gatech.seclass.tourneymanager;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Prize;
import edu.gatech.seclass.tourneymanager.models.Round;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.TourneyInfo;

/**
 * Created by johon on 2/25/2017.
 */

public class ManagerMode extends AppMode
{
    ManagerMode(TourneyManagerApp app)
    {
        super(mode_t.MANAGER);
        m_app = app;
    }

    public boolean createTournament(ArrayList<String> players, int tourneyId, int percent, int fee, Context context) {

        Tournament tournament = new Tournament();
        tournament.setId(tourneyId);
        tournament.setRunning(true);

        TourneyInfo tourneyInfo = new TourneyInfo();
        tourneyInfo.setEntryPrice(fee);
        tourneyInfo.setUserNames(players);
        tourneyInfo.setHousePercent(percent);
        tourneyInfo.setNumberOfEntrants(players.size());
        tourneyInfo.setCalculatedValues();

        tournament.setInfo(tourneyInfo);

        try {
            TourneyManagerDao.saveTournament(tournament, context);
        } catch (Exception e) {
            return false;
        }
        return createRounds(context,tourneyId, players.size());
    }

    private boolean createRounds(Context context, int tId, int nPlayers){

        if(nPlayers%2>0) nPlayers+=1;
        int rounds=0,matches=0;
        ArrayList<Round> roundList = new ArrayList<Round>();

        for(int i=nPlayers;i>1;i=(i/2))
        {
            Round round = new Round(tId);
            round.setId(m_app.getLastRoundId()+1);
            m_app.setLastRoundId(round.getId());
            roundList.add(round);
            round.createMatches(i/2,round.getId(),m_app.getLastMatchId());
            m_app.setLastMatchId(m_app.getLastMatchId()+(i/2));
        }

        try {
            TourneyManagerDao.saveRounds(roundList, context);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean EndTournament(Context context){
        try {
            Tournament t = TourneyManagerDao.GetActiveTournament(context);
            HashMap<Integer, ArrayList<Match>> matchMap = TourneyManagerDao.GetMatchesByTourneyId(t.getId(), context);
            Set<Integer> keys = matchMap.keySet();
            boolean isRoundRunning = false;
            for (Integer i : keys) {
                Round r = TourneyManagerDao.GetRound(t.getId(), i, context);
                isRoundRunning = r.isRunning();
            }
            if (isRoundRunning) {
                t.setEndedEarly(true);
                TourneyManagerDao.updateTournament(t, context);
                return false;

                //method to check if all the rounds are being completed. call refund not.
            }
            else{
                int lastMatchId = m_app.getLastMatchId();
                int secondLastRoundId = m_app.getLastRoundId()-1;

                Match lastMatch = TourneyManagerDao.GetMatchById(lastMatchId, context);
                String winner = lastMatch.getWinner();
                String runnerUp = lastMatch.getWinner().equals(lastMatch.getPlayer1()) ? lastMatch.getPlayer2() : lastMatch.getPlayer1();

                //find the third place playoff
                Round secondLastRound = TourneyManagerDao.GetRound(t.getId(), secondLastRoundId, context);
                List<String> secondLastRoundWinners = secondLastRound.getWinners();
                List<String> secondLastRoundPlayers = new ArrayList<String>();
                List<Match> secondLastRoundMatches = secondLastRound.getMatches();

                for(int i=0; i<secondLastRound.getMatches().size(); i++){
                    secondLastRoundPlayers.add(secondLastRoundMatches.get(i).getPlayer1());
                    secondLastRoundPlayers.add(secondLastRoundMatches.get(i).getPlayer2());
                }
                secondLastRoundPlayers.removeAll(Collections.singletonList(winner));
                secondLastRoundPlayers.removeAll(Collections.singletonList(runnerUp));

                String thirdPlacePlayoff = secondLastRoundPlayers.get(new Random().nextInt(secondLastRoundPlayers.size()));

                //update the player prizes
                addPlayerPrizes(winner, t.getId(), 1, t.getInfo().getFirstPlacePrize(), context);
                addPlayerPrizes(runnerUp, t.getId(), 2, t.getInfo().getSecondPlacePrize(), context);
                addPlayerPrizes(thirdPlacePlayoff, t.getId(), 3, t.getInfo().getThirdPlacePrize(), context);

                t.setFinished(true);
                TourneyManagerDao.updateTournament(t, context);
                return true;
            }
            //method to find if the tournament ended early. Not all the rounds are completed.
            //update the tournament/ matches
        }
        catch(Exception e){
            return false;
        }
    }

    private void addPlayerPrizes(String player, int tId, int place, int money, Context context){
        Prize prize = new Prize();
        prize.setUserName(player);
        prize.setTourneyId(tId);
        prize.setPlace(place);
        prize.setMoneyWon(money);
        TourneyManagerDao.addPrize(prize, context);
    }

    public int CheckRound(int rID, int tID, Context context)
    {
        Round.RoundState state = TourneyManagerDao.GetRoundState(rID,context);
        if(state == Round.RoundState.FINISHED)
        {
            Set<Integer> rIds = TourneyManagerDao.GetMatchesByTourneyId(tID,context).keySet();
            SortedSet<Integer> sorted = new TreeSet<Integer>(rIds);
            Iterator<Integer> i = sorted.iterator();
            while(i.hasNext())
            {
                int tmp = i.next();
                if(tmp==rID && i.hasNext())
                {
                    return SetNextRound(rID,i.next(),tID,context)==true ? 1 : 0;
                }
            }
            return -1;
        }
        return 1;
    }

    public boolean SetNextRound(int curRoundId, int nextRoundId, int tid, Context context)
    {
        if(curRoundId==-1)
        {
            Tournament t = TourneyManagerDao.GetActiveTournament(context);
            Round r = TourneyManagerDao.GetRound(tid,-1,context);
            Iterator<String> n = t.getInfo().getUserNames().iterator();
            for(Match m : r.getMatches())
            {
                if(n.hasNext()) {
                    m.setPlayer1(n.next());
                    if(n.hasNext())
                        m.setPlayer2(n.next());
                    else return false;
                }
                else return false;
                TourneyManagerDao.UpdateMatch(m,context);
            }
        }
        else
        {
            Round r1 = TourneyManagerDao.GetRound(tid,curRoundId,context);
            Round r2 = TourneyManagerDao.GetRound(tid,nextRoundId,context);
            String [] w = r1.getWinners().toArray(new String[r1.getWinners().size()]);
            int nWinners = r1.getWinners().size();
            int i=0;
            for(Match m : r2.getMatches())
            {
                if(i<nWinners-1)
                {
                    m.setPlayer1(w[i++]);
                    m.setPlayer2(w[i++]);
                }
                else
                    return false;
                TourneyManagerDao.UpdateMatch(m,context);
            }

        }

        return true;
    }

    public boolean createPlayer(String name, String username, String phone, String deckChoice, Context context) {
        Player player = new Player();
        player.setName(name);
        player.setUserName(username);
        player.setPhoneNumber(phone);
        player.setDeckChoice(deckChoice);

        try {
            TourneyManagerDao.savePlayer(player, context); //db call
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public boolean updatePlayer(String name, String username, String phone, String deckChoice, Context context) {
        Player player = TourneyManagerDao.GetPlayerByUsername(username, context);
        if(player==null) return false;
        player.setName(name);
        player.setUserName(username);
        player.setPhoneNumber(phone);
        player.setDeckChoice(deckChoice);

        try {
            TourneyManagerDao.UpdatePlayer(player, context); //db call
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private TourneyManagerApp m_app;
}
