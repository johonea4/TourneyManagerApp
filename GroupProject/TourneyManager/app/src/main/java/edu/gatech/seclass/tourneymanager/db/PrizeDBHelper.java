package edu.gatech.seclass.tourneymanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.models.Prize;

/**
 * Created by IndikaP on 3/7/17.
 */

public class PrizeDBHelper extends AbstractHelper {

    private static final String TABLE_PRIZE = "player";

    private static final String KEY_ID = "id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_TOURNAMENT_ID = "tournament_id";
    private static final String KEY_PLACE = "place";
    private static final String KEY_MONEY = "money";

    public PrizeDBHelper(Context context) {
        super(context);
    }

    public static void OnCreate(SQLiteDatabase db) {
        String CREATE_PRIZE_TABLE = "CREATE TABLE " + TABLE_PRIZE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_TOURNAMENT_ID + " INTEGER,"
                + KEY_PLACE + " INTEGER,"
                + KEY_MONEY + " INTEGER"
                + ")";
        db.execSQL(CREATE_PRIZE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIZE);
        // Create tables again
        onCreate(db);
    }

    public void addPrize(Prize prize){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, prize.getUserName()); // Contact user Name
        values.put(KEY_TOURNAMENT_ID, prize.getTourneyId()); // Tourney Id
        values.put(KEY_PLACE, prize.getPlace()); // Place
        values.put(KEY_MONEY, prize.getMoneyWon()); // Money Won

        // Inserting Row
        db.insert(TABLE_PRIZE, null, values);
        db.close(); // Closing database connection
    }

    public List<Prize> getAllPrizes(String userName){

        ArrayList<Prize> prizeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRIZE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Prize prize = new Prize();
                prize.setId(cursor.getInt(0));
                prize.setUserName(cursor.getString(1));
                prize.setTourneyId(Integer.parseInt(cursor.getString(2)));
                prize.setPlace(Integer.parseInt(cursor.getString(3)));
                prize.setMoneyWon(Integer.parseInt(cursor.getString(4)));
                // Adding prize to list
                prizeList.add(prize);
            } while (cursor.moveToNext());
        }

        return prizeList;    }
}
