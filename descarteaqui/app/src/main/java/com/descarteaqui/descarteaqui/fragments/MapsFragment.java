package com.descarteaqui.descarteaqui.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private FloatingActionButton floatbuttonBattery, floatbuttonOil, floatbuttonChemistry;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LatLng Me;
    private GoogleMap map;
    private ImageButton myLocationButton;
    private boolean isInit = true;
    private MarkerOptions myMarker;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        floatbuttonBattery = (FloatingActionButton) rootView.findViewById(R.id.fab_battery);
        floatbuttonOil = (FloatingActionButton) rootView.findViewById(R.id.fab_oil);
        floatbuttonChemistry = (FloatingActionButton) rootView.findViewById(R.id.fab_chemistry);
        myLocationButton = (ImageButton) rootView.findViewById(R.id.myLocation);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                floatButtonClick(v);
            }
        };

        myLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGPSEnable())
                    getCurrentPosition();
                else
                    Toast.makeText(getActivity(),"Erro: É necessário ativar a Localização", Toast.LENGTH_SHORT).show();

            }
        });

        floatbuttonBattery.setOnClickListener(listener);
        floatbuttonOil.setOnClickListener(listener);
        floatbuttonChemistry.setOnClickListener(listener);

        checkPermission();

        return rootView;
    }

    private void getCurrentPosition(){

        if (Me != null)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(Me , 17.0f));
        else
            getCurrentLocation();
    }

    private boolean checkGPSEnable(){
        boolean isGPSenabled = false;
        try {
            isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        return isGPSenabled;
    }

    private void checkPermission() {

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSenabled = checkGPSEnable();

        if (!isGPSenabled) {
            // notify user
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
                    Toast.makeText(getActivity(), "Para usar o mapa, é necessário ativar a Localização", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }

        if (isGPSenabled) {
            getCurrentLocation();
        }
    }

    private void getCurrentLocation() {

        locationManager = (LocationManager) this.getActivity().getSystemService(this.getActivity().LOCATION_SERVICE);

        locationListener = new LocationListener() {
           @Override
           public void onLocationChanged(Location location) {
               Me = new LatLng(location.getLatitude(), location.getLongitude());

               if (isInit) {
                   map.moveCamera(CameraUpdateFactory.newLatLngZoom(Me, 17.0f));
                   myMarker = new MarkerOptions().title("Você").anchor(0.0f, 1.0f).snippet("Você está aqui!").position(Me);
                   map.addMarker(myMarker);
                   isInit = false;
               }
           }

           @Override
           public void onStatusChanged(String provider, int status, Bundle extras) {}

           @Override
           public void onProviderEnabled(String provider) {}

           @Override
           public void onProviderDisabled(String provider) {
               Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
               getActivity().startActivity(myIntent);
           }
        };

        changeLocation();

    }

    private void changeLocation() {
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        locationManager.requestLocationUpdates("gps", 1000, 0, locationListener);
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

        map.getUiSettings().setMapToolbarEnabled(false);

        LatLng UFCG = new LatLng(-7.215192, -35.909692);

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
