package edu.gatech.seclass.tourneymanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rugrani on 3/2/17.
 */

public abstract class AbstractHelper extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tourneydb";

    public AbstractHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
