package com.descarteaqui.descarteaqui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 20/09/2016.
 */
public class TipsDB {

    private SQLiteDatabase db;
    private Database TipsDB;

    private final int FIRST_DAY_INDEX = 3;
    private final int LAST_DAY_INDEX = 9;


    public TipsDB(Context context){
        TipsDB = new Database(context);
        db = TipsDB.getWritableDatabase();

    }

    public void addCEP(String cep, String address ,String[] trashDays) {
        String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        ContentValues valores = new ContentValues();

        valores.put("cep", cep);
        valores.put("address", address);


        int i = 0;
        for (String dayOfWeek:  daysOfWeek) {
            valores.put(dayOfWeek, trashDays[i]);
            i++;
        }

        db.insert(Database.TABLE_TIPS, null, valores);
    }

    public String getAddress(String CEP){
        String address = "";

        String[] colunas = {"_id", "cep", "address", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        Cursor cursor = db.query(Database.TABLE_TIPS, colunas, null, null, null, null, "cep ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                if (CEP.equals(cursor.getString(1))) {
                    address = cursor.getString(2);
                }

            } while(cursor.moveToNext());
        }

        return address;
    }

    public List<String> searchCEP(String CEP){
        List<String> list = new ArrayList<>();

        String[] colunas = {"_id", "cep", "address", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        Cursor cursor = db.query(Database.TABLE_TIPS, colunas, null, null, null, null, "cep ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                if (CEP.equals(cursor.getString(1))) {
                    for (int i = FIRST_DAY_INDEX; i < LAST_DAY_INDEX; i++) {
                        list.add(cursor.getString(i));
                    }
                }

            } while(cursor.moveToNext());
        }

        return list;
    }

    public List<String> getAllCPF(){
        List<String> list = new ArrayList<>();

        String[] colunas = {"_id", "cep", "address", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        Cursor cursor = db.query(Database.TABLE_TIPS, colunas, null, null, null, null, "cep ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                list.add(cursor.getString(1));

            } while(cursor.moveToNext());
        }

        if (list.isEmpty())
            return null;
        else
            return list;
    }
}
