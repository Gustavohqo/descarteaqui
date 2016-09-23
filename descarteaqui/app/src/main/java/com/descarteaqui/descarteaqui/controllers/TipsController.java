package com.descarteaqui.descarteaqui.controllers;

import android.content.Context;

import com.descarteaqui.descarteaqui.database.TipsDB;

import java.util.List;

/**
 * Created by Gabriel on 21/09/2016.
 */
public abstract class TipsController {

    private static TipsDB TipsDB;

    public static void createCEPs(Context ctx){
        addCEP(ctx, "58433-521", new int[]{1, 0, 0, 0, 0, 0, 0});
        addCEP(ctx, "58433-522", new int[]{1, 1, 0, 0, 0, 0, 0});
        addCEP(ctx, "58433-523", new int[]{1, 1, 1, 0, 0, 0, 0});
        addCEP(ctx, "58433-524", new int[]{1, 1, 1, 1, 0, 0, 0});
        addCEP(ctx, "58433-525", new int[]{1, 1, 1, 1, 1, 0, 0});
        addCEP(ctx, "58433-526", new int[]{1, 1, 1, 1, 1, 1, 0});
        addCEP(ctx, "58433-527", new int[]{1, 1, 1, 1, 1, 1, 1});
        addCEP(ctx, "58433-528", new int[]{1, 0, 1, 0, 1, 0, 1});
        addCEP(ctx, "58433-528", new int[]{0, 1, 0, 1, 0, 1, 0});
    }

    public static void addCEP(Context ctx, String CEP, int[] trashDays) {

        TipsDB = new TipsDB(ctx);

        TipsDB.addCEP(CEP, trashDays);

    }

    public static List<Boolean> searchCEP(Context ctx, String CEP){

        TipsDB = new TipsDB(ctx);

        return TipsDB.searchCEP(CEP);
    }

}
