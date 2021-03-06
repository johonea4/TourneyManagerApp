package edu.gatech.seclass.tourneymanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.models.Player;

/**
 * Created by rugrani on 3/2/17.
 */

public class PlayerDBHelper extends AbstractHelper {

    private static final String TABLE_PLAYER = "player";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_DECK_CHOICE = "deck_choice";

    public PlayerDBHelper(Context context) {
        super(context);
    }

    public static void OnCreate(SQLiteDatabase db) {
        String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYER + "("
                + KEY_ID + " INTEGER,"
                + KEY_USER_NAME + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " INTEGER,"
                + KEY_DECK_CHOICE + " TEXT"
                + ")";
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        // Create tables again
        onCreate(db);
    }

    // Adding new player
    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, player.getUserName()); // Contact user Name
        values.put(KEY_NAME, player.getName()); // Contact Name
        values.put(KEY_PH_NO, player.getPhoneNumber()); // Contact Phone Number
        values.put(KEY_DECK_CHOICE, player.getDeckChoice());

        // Inserting Row
        db.insert(TABLE_PLAYER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single player
    public Player getPlayer(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER, new String[] {
                KEY_ID, KEY_NAME, KEY_USER_NAME, KEY_PH_NO, KEY_DECK_CHOICE },
                KEY_USER_NAME + "=?",
                new String[] { userName }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player player = new Player(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return player;
    }

    // Getting single player
    public Player updatePlayer(Player player) {
        Log.d("UPDATE_PLAYER", "updatePlayer: start");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_USER_NAME,player.getUserName());
        cv.put(KEY_NAME,player.getName());
        cv.put(KEY_PH_NO,player.getPhoneNumber());
        cv.put(KEY_DECK_CHOICE,player.getDeckChoice());

        db.update(TABLE_PLAYER, cv, "user_name='"+player.getUserName()+"'", null);
        Log.d("UPDATE_PLAYER", "updatePlayer: success");
        return player;
    }

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setId(cursor.getInt(0));
                player.setUserName(cursor.getString(1));
                player.setName(cursor.getString(2));
                player.setPhoneNumber(cursor.getString(3));
                player.setDeckChoice(cursor.getString(4));
                // Adding player to list
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        return playerList;
    }

    public void deletePlayer(String uName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_USER_NAME + " = ?",
                new String[] { uName });
        db.close();
    }
}
