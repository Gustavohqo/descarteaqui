package com.descarteaqui.descarteaqui.fragments;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private FloatingActionButton floatbuttonBattery, floatbuttonOil, floatbuttonChemistry, floatButtonHospital, floatButtonSelective, floatButtonClear;
    private LocationManager locationManager;
    private GoogleMap map;
    private ArrayList<MarkerOptions> markers = new ArrayList<>();
    private ArrayList<String> options = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        floatbuttonBattery = (FloatingActionButton) rootView.findViewById(R.id.fab_battery);
        floatbuttonOil = (FloatingActionButton) rootView.findViewById(R.id.fab_oil);
        floatbuttonChemistry = (FloatingActionButton) rootView.findViewById(R.id.fab_chemistry);
        floatButtonHospital = (FloatingActionButton) rootView.findViewById(R.id.fab_hospital);
        floatButtonSelective = (FloatingActionButton) rootView.findViewById(R.id.fab_selective);

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

        checkPermission();

        return rootView;
    }

    private boolean checkGPSEnable() {
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

        map.getUiSettings().setMapToolbarEnabled(false);
        map.setMyLocationEnabled(true);

        map.setPadding(0, 170, 0, 0);

        createMarkers();

    }

    private void createMarkers(){

        BitmapDescriptor oil_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_oil_location);
        BitmapDescriptor battery_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_battery_location);
        BitmapDescriptor chemical_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_chemical_location);
        BitmapDescriptor selective_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_selective_location);
        BitmapDescriptor hospital_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital_location);

        MarkerOptions loc1 = new MarkerOptions()
                .position(new LatLng(-7.215192, -35.909692))
                .title("UFCG")
                .anchor(0.0f, 1.0f)
                .snippet("Coleta de Óleo")
                .icon(oil_location);

        MarkerOptions loc2 = new MarkerOptions()
                .position(new LatLng(-7.216374, -35.915185))
                .title("CITTA")
                .anchor(0.0f, 1.0f)
                .snippet("Lixo Eletrônico")
                .icon(battery_location);

        MarkerOptions loc3 = new MarkerOptions()
                .position(new LatLng(-7.218522, -35.902932))
                .title("Lugar aí 1")
                .anchor(0.0f, 1.0f)
                .snippet("Lixo Hospitalar")
                .icon(hospital_location);

        MarkerOptions loc4 = new MarkerOptions()
                .position(new LatLng(-7.219167, -35.912647))
                .title("Lugar aí 2")
                .anchor(0.0f, 1.0f)
                .snippet("Coleta Seletiva")
                .icon(selective_location);

        MarkerOptions loc5 = new MarkerOptions()
                .position(new LatLng(-7.213913, -35.896774))
                .title("Lugar aí 3")
                .anchor(0.0f, 1.0f)
                .snippet("Lixo Químico")
                .icon(chemical_location);

        markers.add(loc1);
        markers.add(loc2);
        markers.add(loc3);
        markers.add(loc4);
        markers.add(loc5);

        addMarkers(markers);
    }

    private void addMarkers(ArrayList<MarkerOptions> markers){
        for (MarkerOptions mark: markers) {
            map.addMarker(mark);
        }
    }

    public void floatButtonClick(View v) {

        switch (v.getId()){
            case R.id.fab_battery:
                filterBy("Eletrônico");
                break;
            case R.id.fab_oil:
                filterBy("Óleo");
                break;
            case R.id.fab_chemistry:
                filterBy("Químico");
                break;
            case R.id.fab_hospital:
                filterBy("Hospitalar");
                break;
            case R.id.fab_selective:
                filterBy("Coleta Seletiva");
                break;
            case R.id.fab_clear_filter:
                options.clear();
                map.clear();
                createMarkers();
                floatButtonClear.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    private void filterBy(String trashType){

        if (!options.isEmpty()) {
            floatButtonClear.setVisibility(View.VISIBLE);
        }

        if (!options.contains(trashType))
            options.add(trashType);

        map.clear();

        for (MarkerOptions mark: markers){
            for (String option: options)
            if (mark.getSnippet().contains(option))
                map.addMarker(mark);
        }
    }

}
