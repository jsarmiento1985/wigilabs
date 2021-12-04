package com.example.wigilabs.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wigilabs.repository.local.db.Db;


/**
 * Created by gio on 2/09/15.
 */
public class Controller {
    protected Context context;
    protected SQLiteDatabase db;

    public Controller(Context context) {
        this.context = context;
        db = Db.getInstance(context);
    }
}
