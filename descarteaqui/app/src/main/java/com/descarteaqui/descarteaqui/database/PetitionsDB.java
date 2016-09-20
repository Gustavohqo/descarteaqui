package com.descarteaqui.descarteaqui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.descarteaqui.descarteaqui.model.Petition;

import java.util.ArrayList;
import java.util.List;

public class PetitionsDB {
    private SQLiteDatabase db;
    private Database PetitionsDB;

    public PetitionsDB(Context context){
        PetitionsDB = new Database(context);
        db = PetitionsDB.getWritableDatabase();

    }

    public void addPetition(Petition petition) {
        ContentValues valores = new ContentValues();

        valores.put("street", petition.getStreetName());
        valores.put("created_at", petition.getCreationDate());
        valores.put("district", petition.getDistrictName());
        valores.put("justification", petition.getJustification());
        valores.put("creator", petition.getCreator());
        valores.put("ok_rates", petition.getRatesOK());
        valores.put("ng_rates", petition.getRatesNG());

        db.insert(Database.TABLE_PETITIONS, null, valores);

    }

    public List<Petition> getPetitions(){
        List<Petition> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "street", "created_at", "district", "justification", "creator", "ok_rates", "ng_rates"};

        Cursor cursor = db.query(Database.TABLE_PETITIONS, colunas, null, null, null, null, "street ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                 int id = cursor.getInt(0);
                 String street = cursor.getString(1);
                 String created_at = cursor.getString(2);
                 String district = cursor.getString(3);
                 String justification = cursor.getString(4);
                 String email = cursor.getString(5);
                 int ok_rates = cursor.getInt(6);
                 int ng_rates = cursor.getInt(7);

                 Petition petition = new Petition(id, street, district, justification, email);
                 petition.setCreationDate(created_at);
                 petition.setRatesNG(ng_rates);
                 petition.setRatesOK(ok_rates);

                 list.add(petition);

            } while(cursor.moveToNext());
        }

        return list;
    }


    public int getLastID(){
        int maior = -1;

        String[] colunas = new String[]{"_id", "street", "created_at", "district", "justification", "creator", "ok_rates", "ng_rates"};
        Cursor cursor = db.query(Database.TABLE_PETITIONS, colunas, null, null, null, null, "street ASC");


        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                int last_id = cursor.getInt(0);
                if (last_id > maior){
                    maior = last_id;
                }

            } while(cursor.moveToNext());
        }
        return maior;
    }

}
