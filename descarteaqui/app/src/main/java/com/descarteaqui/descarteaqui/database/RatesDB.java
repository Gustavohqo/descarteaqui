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
public class RatesDB {

    private SQLiteDatabase db;
    private Database RatesDB;

    public RatesDB(Context context){
        RatesDB = new Database(context);
        db = RatesDB.getWritableDatabase();

    }

    public void addRatedPetition(Context ctx, String rated_by, int petition_id){
        ContentValues valores = new ContentValues();

        valores.put("rated_by", rated_by);
        valores.put("petition_id", petition_id);

        db.insert(Database.TABLE_RATES, null, valores);
    }

    public void removeRatedPetition(Context ctx, String rated_by, int petition_id){
        db.delete(Database.TABLE_RATES, "petition_id = ?", new String[]{""+petition_id});

        db.close();
    }

    public List<Petition> getRatedPetitions(Context ctx, String userEmail){
        PetitionsDB PetitionsDB = new PetitionsDB(ctx);

        List<Petition> ratedPetitions = new ArrayList<>();

        String[] colunas = new String[]{"_id", "rated_by", "petition_id"};

        Cursor cursor = db.query(Database.TABLE_RATES, colunas, null, null, null, null, "rated_by ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                String rated_by = cursor.getString(1);
                int petition_id = cursor.getInt(2);
                Petition currentPetition = PetitionsDB.getPetitionById(petition_id);

                if (rated_by.equals(userEmail))
                    ratedPetitions.add(currentPetition);

            } while(cursor.moveToNext());
        }

        return ratedPetitions;
    }
}
