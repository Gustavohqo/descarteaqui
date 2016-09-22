package com.descarteaqui.descarteaqui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.descarteaqui.descarteaqui.model.Petition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 20/09/2016.
 */
public class TipsDB {

    private SQLiteDatabase db;
    private Database TipsDB;

    private final int FIRST_DAY_INDEX = 2;
    private final int LAST_DAY_INDEX = 3;

    public TipsDB(Context context){
        TipsDB = new Database(context);
        db = TipsDB.getWritableDatabase();

    }

    public void addCEP(String cep, int[] trashDays) {
        ContentValues valores = new ContentValues();

        valores.put("cep", cep);

        String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        for (int i = 0; i < daysOfWeek.length; i++) {
            valores.put(daysOfWeek[i], trashDays[i]);
        }

        db.insert(Database.TABLE_TIPS, null, valores);

    }

    public List<Boolean> searchCEP(String CEP){
        List<Boolean> list = new ArrayList<>();

        String[] colunas = {"_id", "cep", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        Cursor cursor = db.rawQuery("SELECT monday, tuesday, wednesday, thursday, friday, saturday, sunday FROM " + Database.TABLE_TIPS + " WHERE cep = ?", new String[] {CEP});

        if (cursor != null) {
            for (int i = FIRST_DAY_INDEX; i < LAST_DAY_INDEX; i++) {
                list.add(Boolean.getBoolean(String.valueOf(cursor.getInt(i))));
            }
        }

        return list;
    }
}
