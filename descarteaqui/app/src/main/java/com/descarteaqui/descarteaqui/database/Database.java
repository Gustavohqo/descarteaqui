package com.descarteaqui.descarteaqui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "descarteaqui.db";

    // Table Names
    public static final String TABLE_MARKERS = "markers";
    public static final String TABLE_PETITIONS = "petitions";
    public static final String TABLE_RATES = "rates";
    public static final String TABLE_TIPS = "tips";


    // Table Creating
    public static final String CREATE_TABLE_MARKERS = "create table "+ TABLE_MARKERS +"("
            + "_id" + " integer primary key autoincrement, "
            + "title" + " text, "
            + "latitude" + " text, "
            + "longitude" + " text, "
            + "snippet" + " text, "
            + "icon" + " integer"
            +");";

    public static final String CREATE_TABLE_PETITIONS = "create table "+ TABLE_PETITIONS +"("
            + "_id" + " integer primary key autoincrement, "
            + "street" + " text, "
            + "created_at" + " text, "
            + "district" + " text, "
            + "justification" + " text, "
            + "creator" + " text, "
            + "ok_rates" + " integer, "
            + "ng_rates" + " integer"
            +");";

    public static final String CREATE_TABLE_RATES = "create table "+ TABLE_RATES +"("
            + "_id" + " integer primary key autoincrement, "
            + "rated_by" + " text, "
            + "petition_id" + " integer, "
            + "type_rate" + " text"
            +");";

    public static final String CREATE_TABLE_TIPS = "create table "+ TABLE_TIPS +"("
            + "_id" + " integer primary key autoincrement, "
            + "cep" + " text, "
            + "address" + " text, "
            + "monday" + " text, "
            + "tuesday" + " text, "
            + "wednesday" + " text, "
            + "thursday" + " text, "
            + "friday" + " text, "
            + "saturday" + " text, "
            + "sunday" + " text"
            +");";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_MARKERS);
        db.execSQL(CREATE_TABLE_PETITIONS);
        db.execSQL(CREATE_TABLE_RATES);
        db.execSQL(CREATE_TABLE_TIPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("drop table if exists " + TABLE_PETITIONS);
        db.execSQL("drop table if exists " + TABLE_MARKERS);
        db.execSQL("drop table if exists " + TABLE_RATES);
        db.execSQL("drop table if exists " + TABLE_TIPS);

        // create new tables
        onCreate(db);
    }

}
