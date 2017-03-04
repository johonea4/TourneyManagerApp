package edu.gatech.seclass.tourneymanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.models.Match;


/**
 * Created by rugrani on 3/3/17.
 */

public class MatchDBHelper extends AbstractHelper{
    private static final String TABLE_MATCH = "match";

    private static final String KEY_ID = "id";
    private static final String KEY_WINNER = "winner";
    private static final String KEY_PLAYER1 = "player1";
    private static final String KEY_PLAYER2 = "player2";
    private static final String KEY_IS_RUNNING = "is_running";
    private static final String KEY_IS_FINISHED = "is_finished";

    public MatchDBHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MATCH_TABLE = "CREATE TABLE " + TABLE_MATCH + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_WINNER + " TEXT,"
                + KEY_PLAYER1 + " TEXT,"
                + KEY_PLAYER2 + " TEXT,"
                + KEY_IS_RUNNING + " INT,"
                + KEY_IS_FINISHED + " INT"
                + ")";
        db.execSQL(CREATE_MATCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCH);
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

    public void addMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, match.getId());
        values.put(KEY_WINNER, match.getWinners());
        values.put(KEY_PLAYER1, match.getPlayer1());
        values.put(KEY_PLAYER2, match.getPlayer2());
        values.put(KEY_IS_RUNNING, intforboolean(match.isRunning()));
        values.put(KEY_IS_FINISHED, intforboolean(match.isFinished()));

        db.insert(TABLE_MATCH, null, values);
        db.close();
    }

    public Match getMatch(int key) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MATCH, new String[] {
                        KEY_ID, KEY_WINNER, KEY_PLAYER1, KEY_PLAYER2, KEY_IS_RUNNING, KEY_IS_FINISHED },
                KEY_ID + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Match match = new Match(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                booleanforint(Integer.parseInt(cursor.getString(4))), booleanforint(Integer.parseInt(cursor.getString(5))));
        return match;
    }

    public List<Match> getAllMatches() {
        List<Match> matchList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MATCH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Match match = new Match();
                match.setId(Integer.parseInt(cursor.getString(0)));
                match.setWinners(cursor.getString(1));
                match.setPlayer1(cursor.getString(2));
                match.setPlayer2(cursor.getString(3));
                match.setRunning(booleanforint(Integer.parseInt(cursor.getString(4))));
                match.setFinished(booleanforint(Integer.parseInt(cursor.getString(5))));

                matchList.add(match);
            } while (cursor.moveToNext());
        }

        return matchList;
    }


}
