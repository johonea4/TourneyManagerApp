package edu.gatech.seclass.tourneymanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edu.gatech.seclass.tourneymanager.models.Prize;

/**
 * Created by IndikaP on 3/7/17.
 */

public class PrizeDBHelper extends AbstractHelper {

    public PrizeDBHelper(Context context) {
        super(context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Prize> getPrizes(String userName){

        return null;
    }
}
