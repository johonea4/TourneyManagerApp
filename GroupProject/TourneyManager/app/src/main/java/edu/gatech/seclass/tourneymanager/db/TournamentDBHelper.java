package edu.gatech.seclass.tourneymanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.models.Tournament;

/**
 * Created by rugrani on 3/2/17.
 */

public class TournamentDBHelper extends AbstractHelper {

    private static final String TABLE_TOURNAMENT = "tournament";

    private static final String KEY_ID = "id";
    private static final String KEY_IS_RUNNING = "is_running";
    private static final String KEY_IS_ENDED_EARLY = "is_ended_early";

    public TournamentDBHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_TOURNAMENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IS_RUNNING + " INT,"
                + KEY_IS_ENDED_EARLY + " INT"
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

        db.update(TABLE_TOURNAMENT, values, "id="+tournament.getId(), null);
        db.close();
    }

    public Tournament getTournament(int key) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TOURNAMENT, new String[] {
                        KEY_ID, KEY_IS_RUNNING, KEY_IS_ENDED_EARLY },
                KEY_ID + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tournament tournament = new Tournament(Integer.parseInt(cursor.getString(0)),
                booleanforint(Integer.parseInt(cursor.getString(0))), booleanforint(Integer.parseInt(cursor.getString(0))));
        return tournament;
    }

    public List<Tournament> getAllTournament() {
        List<Tournament> tournamentList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TOURNAMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tournament tournament = new Tournament();
                tournament.setId(Integer.parseInt(cursor.getString(0)));
                tournament.setRunning(booleanforint(Integer.parseInt(cursor.getString(1))));
                tournament.setEndedEarly(booleanforint(Integer.parseInt(cursor.getString(2))));
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
