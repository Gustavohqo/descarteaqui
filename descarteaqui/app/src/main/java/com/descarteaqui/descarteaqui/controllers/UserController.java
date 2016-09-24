package com.descarteaqui.descarteaqui.controllers;

import android.content.Context;

import com.descarteaqui.descarteaqui.database.RatesDB;
import com.descarteaqui.descarteaqui.model.Petition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 16/09/2016.
 */
public abstract class UserController {

    private static RatesDB RatesDB;
    private static String userEmail = "";

    public static String getCurrentUser() {
        return userEmail;
    }

    public static void setCurrentUser(String currentUser){
        userEmail = currentUser;
    }

    public static boolean isUserLogged(){
        return !userEmail.equals("");
    }

    public static void removeRatedPetition(Context ctx, String rated_by, int petition_id){
        RatesDB = new RatesDB(ctx);

        RatesDB.removeRatedPetition(ctx, rated_by, petition_id);
    }

    public static void addRatedPetition(Context ctx, String rated_by, int petition_id, String currentUser, String rate){
        RatesDB = new RatesDB(ctx);
        
        List<Petition> myPetitions = RatesDB.getRatedPetitions(ctx, currentUser);

        int petitionIDCount = 0;

        for (int i = 0; i < myPetitions.size(); i++) {
            if (myPetitions.get(i).getID() == petition_id){
                petitionIDCount++;
            }
        }

        if (petitionIDCount == 0){
            RatesDB.addRatedPetition(ctx, rated_by, petition_id, rate);
        }
    }

    public static List<Petition> getRatedPetitions(Context ctx, String currentUser) {
        List<Petition> ratedPetitions;

        RatesDB = new RatesDB(ctx);

        ratedPetitions = RatesDB.getRatedPetitions(ctx, currentUser);

        return ratedPetitions;
    }

    public static String getTypeRate(Context ctx, int petition_id, String currentUser){
        RatesDB = new RatesDB(ctx);

        return RatesDB.getTypeRate(petition_id, currentUser);
    }

}
