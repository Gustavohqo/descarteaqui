package com.descarteaqui.descarteaqui.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;

import com.descarteaqui.descarteaqui.controllers.MarkersController;
import com.descarteaqui.descarteaqui.database.MarkersDB;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private FloatingActionButton floatbuttonBattery, floatbuttonOil, floatbuttonChemistry, floatButtonHospital, floatButtonSelective, floatButtonClear;
    private LocationManager locationManager;
    private GoogleMap map;
    private List<MarkerOptions> markers = new ArrayList<>();
    private ArrayList<String> options = new ArrayList<>();
    private TextView GPSError;
    private MarkersDB MarkersDB;

    private static final float DEFAULT_SCALE = 1;
    private static final float MIN_SCALE = 0;
    private static final long ANIMATION_DURATION = 200;
    private static final String DEFAULT_COLOR = "#fc9b0d";
    private static final String CLICKED_COLOR = "#bf7018";

    @Override
    public void onResume() {

        checkPermission();

        if (!checkGPSEnable()) {
            GPSError.setVisibility(View.VISIBLE);
        } else {
            if (map != null) {
                createMarkers();
            }
            GPSError.setVisibility(View.INVISIBLE);
        }

        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        floatbuttonBattery = (FloatingActionButton) rootView.findViewById(R.id.fab_battery);
        floatbuttonOil = (FloatingActionButton) rootView.findViewById(R.id.fab_oil);
        floatbuttonChemistry = (FloatingActionButton) rootView.findViewById(R.id.fab_chemistry);
        floatButtonHospital = (FloatingActionButton) rootView.findViewById(R.id.fab_hospital);
        floatButtonSelective = (FloatingActionButton) rootView.findViewById(R.id.fab_selective);
        GPSError = (TextView) rootView.findViewById(R.id.gps_error);

        GPSError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        floatButtonClear = (FloatingActionButton) rootView.findViewById(R.id.fab_clear_filter);
        floatButtonClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Limpar filtro.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                floatButtonClick(v);
            }
        };

        floatbuttonBattery.setOnClickListener(listener);
        floatbuttonOil.setOnClickListener(listener);
        floatbuttonChemistry.setOnClickListener(listener);
        floatButtonSelective.setOnClickListener(listener);
        floatButtonHospital.setOnClickListener(listener);
        floatButtonClear.setOnClickListener(listener);


        MarkersDB = new MarkersDB(getActivity());
        markers = MarkersDB.getMarkers();

        // Populate the database
        if (markers == null) {
            MarkersController.createMarkers(getActivity());
        }

        return rootView;
    }

    private boolean checkGPSEnable() {
        boolean isGPSenabled = false;
        try {
            isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return isGPSenabled;
    }

    private void checkPermission() {

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSenabled = checkGPSEnable();

        if (!isGPSenabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());
            dialog.setMessage("Por favor, ative a Localização");
            dialog.setPositiveButton("Ativar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getActivity().startActivity(myIntent);
                }
            });
            dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Toast.makeText(getActivity(), "Por favor, ative a Localização", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        this.map = map;

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }

        map.setMyLocationEnabled(true);

        map.setPadding(0, 170, 0, 0);
        map.getUiSettings().setMapToolbarEnabled(false);

        // Create the markers in map
        if (checkGPSEnable())
            createMarkers();

    }



    private void createMarkers(){
        markers = MarkersDB.getMarkers();

        for (MarkerOptions marker: markers) {
            map.addMarker(marker);
        }
    }


    public void floatButtonClick(View v) {

        switch (v.getId()){
            case R.id.fab_battery:

                setFABColor(floatbuttonBattery);
                filterBy("Eletrônico");
                break;

            case R.id.fab_oil:

                setFABColor(floatbuttonOil);
                filterBy("Óleo");
                break;

            case R.id.fab_chemistry:

                setFABColor(floatbuttonChemistry);
                filterBy("Químico");
                break;

            case R.id.fab_hospital:

                setFABColor(floatButtonHospital);
                filterBy("Hospitalar");
                break;

            case R.id.fab_selective:

                setFABColor(floatButtonSelective);
                filterBy("Coleta Seletiva");
                break;

            case R.id.fab_clear_filter:

                options.clear();
                map.clear();
                createMarkers();
                floatButtonClear.animate().scaleX(MIN_SCALE).scaleY(MIN_SCALE).start();
                defaultColorAll();
                break;
        }

    }

    private void setFABColor(FloatingActionButton floatButton){
        if (floatButton.getColorNormal() == Color.parseColor(DEFAULT_COLOR))
                floatButton.setColorNormal(Color.parseColor(CLICKED_COLOR));
        else
            floatButton.setColorNormal(Color.parseColor(DEFAULT_COLOR));
    }

    private void defaultColorAll(){
        floatbuttonBattery.setColorNormal(Color.parseColor(DEFAULT_COLOR));
        floatbuttonChemistry.setColorNormal(Color.parseColor(DEFAULT_COLOR));
        floatButtonSelective.setColorNormal(Color.parseColor(DEFAULT_COLOR));
        floatButtonHospital.setColorNormal(Color.parseColor(DEFAULT_COLOR));
        floatbuttonOil.setColorNormal(Color.parseColor(DEFAULT_COLOR));
    }

    private void filterBy(String trashType){

        if (!options.contains(trashType))
            options.add(trashType);
        else
            options.remove(trashType);

        map.clear();

        for (MarkerOptions mark: markers){
            for (String option: options)
            if (mark.getSnippet().contains(option))
                map.addMarker(mark);
        }

        if (!options.isEmpty())
            floatButtonClear.animate().scaleX(DEFAULT_SCALE).scaleY(DEFAULT_SCALE).setDuration(ANIMATION_DURATION).start();
        else
            floatButtonClick(floatButtonClear);

    }

}