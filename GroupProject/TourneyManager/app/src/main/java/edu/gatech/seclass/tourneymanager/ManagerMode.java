package edu.gatech.seclass.tourneymanager;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Match;
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

    public boolean createTournament(ArrayList<String> players, int tourneyId, int cutVal, int fVal, int sVal, int tVal, Context context) {

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
            round.setId(tId+i+1);
            roundList.add(round);
            round.createMatches(i/2,round.getId());
        }

        try {
            TourneyManagerDao.saveRounds(roundList, context);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean SetNextRound(int curRoundId, int tid, Context context)
    {
        if(curRoundId==-1)
        {
            int rid = tid+1;
            Tournament t = TourneyManagerDao.GetActiveTournament(context);
            Round r = TourneyManagerDao.GetRound(tid,rid,context);
            String [] n = t.getInfo().getUserNames().toArray(new String[t.getInfo().getUserNames().size()]);
            int i=0;
            for(Match m : r.getMatches())
            {
                if(i<t.getInfo().getNumberOfEntrants()-1) {
                    m.setPlayer1(n[i++]);
                    m.setPlayer2(n[i++]);
                }
                else return false;
                TourneyManagerDao.UpdateMatch(m,context);
            }
        }
        else
        {
            Round r1 = TourneyManagerDao.GetRound(tid,curRoundId,context);
            Round r2 = TourneyManagerDao.GetRound(tid,curRoundId+1,context);
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
}
