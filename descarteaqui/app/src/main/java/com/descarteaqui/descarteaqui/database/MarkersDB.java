package com.descarteaqui.descarteaqui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.descarteaqui.descarteaqui.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MarkersDB {
    private SQLiteDatabase db;
    private Database MarkersDB;

    public MarkersDB(Context context){
        MarkersDB = new Database(context);
        db = MarkersDB.getWritableDatabase();

    }

    public void addMarkers(List<MarkerOptions> markers) {
        ContentValues valores = new ContentValues();

        for (MarkerOptions marker :markers) {
            valores.put("title", marker.getTitle());
            valores.put("latitude", String.valueOf(marker.getPosition().latitude));
            valores.put("longitude", String.valueOf(marker.getPosition().longitude));
            valores.put("snippet", marker.getSnippet());
            valores.put("icon", getMarkerIcon(marker.getSnippet()));

            db.insert(Database.TABLE_MARKERS, null, valores);
        }

    }

    public void addMarker(MarkerOptions marker) {
        ContentValues valores = new ContentValues();

        valores.put("title", marker.getTitle());
        valores.put("latitude", String.valueOf(marker.getPosition().latitude));
        valores.put("longitude", String.valueOf(marker.getPosition().longitude));
        valores.put("snippet", marker.getSnippet());
        valores.put("icon", getMarkerIcon(marker.getSnippet()));

        db.insert(Database.TABLE_MARKERS, null, valores);

    }

    public List<MarkerOptions> getMarkers(){
        List<MarkerOptions> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "title", "latitude", "longitude", "snippet", "icon"};

        Cursor cursor = db.query(Database.TABLE_MARKERS, colunas, null, null, null, null, "title ASC");

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {

                String title = cursor.getString(1);
                double latitude = cursor.getDouble(2);
                double longitude = cursor.getDouble(3);
                String snippet = cursor.getString(4);
                int icon = cursor.getInt(5);

                BitmapDescriptor markerIcon = BitmapDescriptorFactory.fromResource(icon);

                MarkerOptions marker = new MarkerOptions()
                        .title(title)
                        .snippet(snippet)
                        .position(new LatLng(latitude, longitude))
                        .icon(markerIcon);

                list.add(marker);

            } while(cursor.moveToNext());
        }

        if (list.isEmpty())
            return null;
        else
            return list;
    }

    private int getMarkerIcon(String snippet){
        int icon = 0;

        switch (snippet){
            case "Lixo Eletrônico":
                icon = R.drawable.ic_battery_location;
                break;
            case "Lixo Químico":
                icon = R.drawable.ic_chemical_location;
                break;
            case "Lixo Hospitalar":
                icon = R.drawable.ic_hospital_location;
                break;
            case "Coleta Seletiva":
                icon = R.drawable.ic_selective_location;
                break;
            case "Coleta de Óleo":
                icon = R.drawable.ic_oil_location;
                break;
        }

        return icon;
    }

}
