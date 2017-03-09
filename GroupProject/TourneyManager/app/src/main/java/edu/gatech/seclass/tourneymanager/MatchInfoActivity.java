package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.Dao.TourneyManagerDao;
import edu.gatech.seclass.tourneymanager.models.Match;

public class MatchInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        m_app = (TourneyManagerApp)getApplication();
        m_mode = m_app.getappMode();
        matchID = (EditText)findViewById(R.id.matchIdField);
        matchID.setInputType(InputType.TYPE_NULL);
        player1 = (EditText)findViewById(R.id.player1Field);
        player1.setInputType(InputType.TYPE_NULL);
        player2 = (EditText)findViewById(R.id.player2Field);
        player2.setInputType(InputType.TYPE_NULL);
        winner = (EditText)findViewById(R.id.winnerField);
        matchStart = (Button)findViewById(R.id.matchStartButton);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        id = i.getIntExtra("matchID",-1);
        if(id==-1) { finish(); return; }
        match = TourneyManagerDao.GetMatchById(id,MatchInfoActivity.this);
        matchID.setText(String.valueOf(id));
        p1 = match.getPlayer1();
        p2 = match.getPlayer2();
        player1.setText(p1==null ? "" : p1);
        player2.setText(p2==null ? "" : p2);
        setUI();
    }
    public void setUI()
    {
        if(match.isRunning())
        {
            winner.setVisibility(View.INVISIBLE);
            matchStart.setText("End Match");
            matchStart.setVisibility(View.VISIBLE);
        }
        else if(match.isFinished())
        {
            winner.setVisibility(View.VISIBLE);
            winner.setText(match.getWinner());
            matchStart.setVisibility(View.INVISIBLE);
        }
        else
        {
            winner.setVisibility(View.INVISIBLE);
            matchStart.setText("Start Match");
            matchStart.setVisibility(View.VISIBLE);
        }
        if(m_mode.getMode() == AppMode.mode_t.PLAYER)matchStart.setVisibility(View.INVISIBLE);
    }

    public void onMatchStartClick(View view)
    {
        if(p1==null || p2==null || p1.isEmpty() || p2.isEmpty())
        {
            Toast.makeText(MatchInfoActivity.this,"Match is not ready to start!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!match.isRunning() && !match.isFinished())
        {
            match.setRunning(true);
            match.setFinished(false);
            setUI();
            TourneyManagerDao.UpdateMatch(match,MatchInfoActivity.this);
        }
        else if(match.isRunning() && !match.isFinished())
        {
            match.setRunning(false);
            match.setFinished(true);
            final AlertDialog.Builder builder = new AlertDialog.Builder(MatchInfoActivity.this);
            builder.setTitle("Choose Winner");
            View mView = getLayoutInflater().inflate(R.layout.dialog_select_winner,null);
            Button p1Button = (Button)mView.findViewById(R.id.player1Button);
            Button p2Button = (Button)mView.findViewById(R.id.player2Button);
            builder.setView(mView);
            final AlertDialog dialog = builder.create();
            //TODO: Set On Click Listeners
            p1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    match.setWinners(match.getPlayer1());
                    setUI();
                    TourneyManagerDao.UpdateMatch(match,MatchInfoActivity.this);
                    int rtn = ((ManagerMode)m_mode).CheckRound(match.getRoundId(),match.getTournamentId(),MatchInfoActivity.this);
                    if(rtn<0)Toast.makeText(MatchInfoActivity.this,"All Rounds have been completed",Toast.LENGTH_SHORT).show();
                    else if(rtn ==0) Toast.makeText(MatchInfoActivity.this,"An Error occured when checking the Round States",Toast.LENGTH_SHORT).show();
                    dialog.hide();
                }
            });
            p2Button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    match.setWinners(match.getPlayer2());
                    setUI();
                    TourneyManagerDao.UpdateMatch(match,MatchInfoActivity.this);
                    int rtn = ((ManagerMode)m_mode).CheckRound(match.getRoundId(),match.getTournamentId(),MatchInfoActivity.this);
                    if(rtn<0)Toast.makeText(MatchInfoActivity.this,"All Rounds have been completed",Toast.LENGTH_SHORT).show();
                    else if(rtn ==0) Toast.makeText(MatchInfoActivity.this,"An Error occured when checking the Round States",Toast.LENGTH_SHORT).show();
                    dialog.hide();
                }
            });
            dialog.show();
        }
    }
    //TODO: Populate Fields
    //TODO: Set Button listener and record match state

    private TourneyManagerApp m_app;
    private AppMode m_mode;
    private EditText matchID;
    private EditText player1;
    private EditText player2;
    private EditText winner;
    private Button matchStart;
    private int id;
    private Match match;
    private String p1;
    private String p2;
}
