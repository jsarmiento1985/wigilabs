package com.example.wigilabs.repository.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gio on 2/09/15.
 */
public class Db {
    private static SQLiteDatabase INSTANCE = null;

    private Db(){}

    private synchronized static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DbHelper(context).getReadableDatabase();
        }
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (INSTANCE == null) createInstance(context);
        return INSTANCE;
    }
}
