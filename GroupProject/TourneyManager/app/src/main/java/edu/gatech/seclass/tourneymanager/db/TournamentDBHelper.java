package edu.gatech.seclass.tourneymanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.TourneyInfo;

/**
 * Created by rugrani on 3/2/17.
 */

public class TournamentDBHelper extends AbstractHelper {

    private static final String TABLE_TOURNAMENT = "tournament";

    private static final String KEY_ID = "id";
    private static final String KEY_IS_RUNNING = "is_running";
    private static final String KEY_IS_ENDED_EARLY = "is_ended_early";
    private static final String KEY_HOUSE_PERCENT = "KEY_HOUSE_PERCENT";
    private static final String KEY_PLAYER_FEE = "KEY_PLAYER_FEE";
    private static final String KEY_NUM_ENTRANTS = "KEY_NUM_ENTRANTS";
    private static final String KEY_PLAYER_LIST = "KEY_PLAYER_LIST";

    public TournamentDBHelper(Context context) {
        super(context);
    }

    public static void OnCreate(SQLiteDatabase db) {
        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_TOURNAMENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IS_RUNNING + " INT,"
                + KEY_IS_ENDED_EARLY + " INT,"
                + KEY_HOUSE_PERCENT + " INT,"
                + KEY_PLAYER_FEE + " INT,"
                + KEY_NUM_ENTRANTS + " INT,"
                + KEY_PLAYER_LIST + " TEXT"
                + ")";
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOURNAMENT);
        // Create tables again
        onCreate(db);
    }

    private int intforboolean(boolean value){
        if(value){
            return 1;
        }else{
            return 0;
        }
    }

    private boolean booleanforint(int value){
        if(value == 1){
            return true;
        }else{
            return false;
        }
    }

    // Adding new player
    public void addTournament(Tournament tournament) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, tournament.getId());
        values.put(KEY_IS_RUNNING, intforboolean(tournament.isRunning()));
        values.put(KEY_IS_ENDED_EARLY, intforboolean(tournament.isEndedEarly()));
        values.put(KEY_HOUSE_PERCENT, tournament.getInfo().getHousePercent());
        values.put(KEY_PLAYER_FEE, tournament.getInfo().getEntryPrice());
        values.put(KEY_NUM_ENTRANTS, tournament.getInfo().getNumberOfEntrants());
        String pList = "";
        for(String un : tournament.getInfo().getUserNames())
        {
            pList += un + ';';
        }
        values.put(KEY_PLAYER_LIST,pList);

        // Inserting Row
        db.insert(TABLE_TOURNAMENT, null, values);
        db.close(); // Closing database connection
    }


    // Adding new player
    public void updateTournament(Tournament tournament) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, tournament.getId());
        values.put(KEY_IS_RUNNING, intforboolean(tournament.isRunning()));
        values.put(KEY_IS_ENDED_EARLY, intforboolean(tournament.isEndedEarly()));
        values.put(KEY_HOUSE_PERCENT, tournament.getInfo().getHousePercent());
        values.put(KEY_PLAYER_FEE, tournament.getInfo().getEntryPrice());
        values.put(KEY_NUM_ENTRANTS, tournament.getInfo().getNumberOfEntrants());
        String pList = "";
        for(String un : tournament.getInfo().getUserNames())
        {
            pList += un + ';';
        }
        values.put(KEY_PLAYER_LIST,pList);
        db.update(TABLE_TOURNAMENT, values, "id="+tournament.getId(), null);
        db.close();
    }

    public Tournament getTournament(int key) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TOURNAMENT, new String[] {
                        KEY_ID, KEY_IS_RUNNING, KEY_IS_ENDED_EARLY, KEY_HOUSE_PERCENT,
                        KEY_PLAYER_FEE, KEY_NUM_ENTRANTS, KEY_PLAYER_LIST},
                KEY_ID + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tournament tournament = new Tournament(Integer.parseInt(cursor.getString(0)),
                booleanforint(Integer.parseInt(cursor.getString(1))),
                booleanforint(Integer.parseInt(cursor.getString(2))));

        TourneyInfo info = new TourneyInfo();
        tournament.setInfo(info);
        tournament.getInfo().setHousePercent(cursor.getInt(3));
        tournament.getInfo().setEntryPrice(cursor.getInt(4));
        tournament.getInfo().setNumberOfEntrants(cursor.getInt(5));
        tournament.getInfo().setCalculatedValues();
        List<String> tok;
        ArrayList<String> uNames = new ArrayList<String>();
        String pList = cursor.getString(6);
        tok = Arrays.asList(pList.split(";"));
        for(String s : tok)
        {
            if(s!=null && !s.isEmpty())
            {
                uNames.add(s);
            }
        }
        tournament.getInfo().setUserNames(uNames);

        return tournament;
    }

    public List<Tournament> getAllTournament() {
        List<Tournament> tournamentList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TOURNAMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tournament tournament = new Tournament();
                tournament.setId(Integer.parseInt(cursor.getString(0)));
                tournament.setRunning(booleanforint(Integer.parseInt(cursor.getString(1))));
                tournament.setEndedEarly(booleanforint(Integer.parseInt(cursor.getString(2))));
                TourneyInfo info = new TourneyInfo();
                tournament.setInfo(info);
                tournament.getInfo().setHousePercent(cursor.getInt(3));
                tournament.getInfo().setEntryPrice(cursor.getInt(4));
                tournament.getInfo().setNumberOfEntrants(cursor.getInt(5));
                tournament.getInfo().setCalculatedValues();
                List<String> tok;
                ArrayList<String> uNames = new ArrayList<String>();
                String pList = cursor.getString(6);
                tok = Arrays.asList(pList.split(";"));
                for(String s : tok)
                {
                    if(s!=null && !s.isEmpty())
                    {
                        uNames.add(s);
                    }
                }
                tournament.getInfo().setUserNames(uNames);
                // Adding player to list
                tournamentList.add(tournament);
            } while (cursor.moveToNext());
        }

        return tournamentList;
    }

    public void deleteTournament(Tournament tournament) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOURNAMENT, KEY_ID + " = ?",
                new String[] { String.valueOf(tournament.getId()) });
        db.close();
    }
}
