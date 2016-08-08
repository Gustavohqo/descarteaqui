package com.descarteaqui.descarteaqui.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.descarteaqui.descarteaqui.R;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {


    private FloatingActionButton floatbuttonBattery, floatbuttonOil, floatbuttonChemistry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        floatbuttonBattery = (FloatingActionButton) rootView.findViewById(R.id.fab_battery);
        floatbuttonOil = (FloatingActionButton) rootView.findViewById(R.id.fab_oil);
        floatbuttonChemistry = (FloatingActionButton) rootView.findViewById(R.id.fab_chemistry);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v){
                floatButtonClick(v);
            }
        };

        floatbuttonBattery.setOnClickListener(listener);
        floatbuttonOil.setOnClickListener(listener);
        floatbuttonChemistry.setOnClickListener(listener);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.getUiSettings().setMapToolbarEnabled(false);

        LatLng UFCG = new LatLng(-7.215192, -35.909692);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(UFCG , 17.0f) );

        map.addMarker(new MarkerOptions()
                .position(UFCG)
                .title("UFCG")
                .anchor(0.0f, 1.0f)
                .snippet("Universidade Federal de Campina Grande"));

    }

    public void floatButtonClick(View v) {

        switch (v.getId()){
            case R.id.fab_battery:

                break;
            case R.id.fab_oil:

                break;
            case R.id.fab_chemistry:

                break;
            default:
                break;
        }
    }
}
