package com.descarteaqui.descarteaqui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rafaelsousa on 16/09/16.
 */
public final class GeneralMethods{


    public static void saveData(Context con, String variable, String data)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        prefs.edit().putString(variable, data).apply();
    }

    public static String getData(Context con, String variable, String defaultValue)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String data = prefs.getString(variable, defaultValue);
        return data;
    }
}
