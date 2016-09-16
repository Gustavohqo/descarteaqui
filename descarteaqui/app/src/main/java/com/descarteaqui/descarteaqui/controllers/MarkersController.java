package com.descarteaqui.descarteaqui.controllers;

import android.content.Context;
import android.util.Log;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.database.MarkersDB;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 15/09/2016.
 */
public abstract class MarkersController {

    private static MarkersDB MarkersDB;

    public static void createMarkers(Context ctx){

        MarkersDB = new MarkersDB(ctx);

        createOilMarkers();
        createEletronicMarkers();
        createChemicalMarkers();
        createHospitalMarkers();
        createSelectiveMarkers();

    }

    private static void createOilMarkers(){
        BitmapDescriptor oil_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_oil_location);
        List<MarkerOptions> lista = new ArrayList<>();
        LatLng[] latlng = {new LatLng(-7.1991876, -35.85548665), new LatLng(-7.17649343, -35.84892666), new LatLng(-7.18915998, -35.86560359), new LatLng(-7.16969233, -35.90484217),
                new LatLng(-7.26861629, -35.86157226), new LatLng(-7.27434766, -35.86570602), new LatLng(-7.25150696, -35.90516683), new LatLng(-7.21572096, -35.8779555),
                new LatLng(-7.21284196, -35.83664641), new LatLng(-7.19324969, -35.8683827)};
        String[] titles = {"", "", "", "", "", "", "", "", "", ""};

        for (int i = 0; i < 10; i++) {
            lista.add(new MarkerOptions()
                    .position(latlng[i])
                    .title(titles[i])
                    .anchor(0.0f, 1.0f)
                    .snippet("Coleta de Óleo")
                    .icon(oil_location));
        }

        MarkersDB.addMarkers(lista);
    }

    private static void createEletronicMarkers(){
        BitmapDescriptor battery_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_battery_location);

        List<MarkerOptions> lista = new ArrayList<>();
        LatLng[] latlng = {new LatLng(-7.20807384, -35.87098143), new LatLng(-7.23346037, -35.89644203), new LatLng(-7.18269464, -35.89553743), new LatLng(-7.24224729, -35.91553884),
                new LatLng(-7.19337866, -35.8637248), new LatLng( -7.2726608, -35.886232), new LatLng(-7.27283574, -35.89518909), new LatLng(-7.24209546, -35.91731032),
                new LatLng(-7.17622356, -35.87520839), new LatLng(-7.21020583, -35.87689379)};
        String[] titles = {"", "", "", "", "", "", "", "", "", ""};

        for (int i = 0; i < 10; i++) {
            lista.add(new MarkerOptions()
                    .position(latlng[i])
                    .title(titles[i])
                    .anchor(0.0f, 1.0f)
                    .snippet("Lixo Eletrônico")
                    .icon(battery_location));
        }

        MarkersDB.addMarkers(lista);
    }

    private static void createChemicalMarkers(){
        BitmapDescriptor chemical_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_chemical_location);

        List<MarkerOptions> lista = new ArrayList<>();
        LatLng[] latlng = {new LatLng(-7.19046555, -35.89644291), new LatLng(-7.1896867, -35.83015996), new LatLng(-7.2702119, -35.8602493), new LatLng(-7.22734723, -35.93228237),
                new LatLng(-7.25690892, -35.86485985), new LatLng(-7.20213296, -35.82491069), new LatLng(-7.26206084, -35.83827296), new LatLng(-7.20621312, -35.83262363),
                new LatLng(-7.20820171, -35.85163801), new LatLng(-7.232672, -35.85897849)};
        String[] titles = {"", "", "", "", "", "", "", "", "", ""};

        for (int i = 0; i < 10; i++) {
            lista.add(new MarkerOptions()
                    .position(latlng[i])
                    .title(titles[i])
                    .anchor(0.0f, 1.0f)
                    .snippet("Lixo Químico")
                    .icon(chemical_location));
        }

        MarkersDB.addMarkers(lista);
    }

    private static void createHospitalMarkers(){
        BitmapDescriptor hospital_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_hospital_location);

        List<MarkerOptions> lista = new ArrayList<>();
        LatLng[] latlng = {new LatLng(-7.22350328, -35.93372557), new LatLng(-7.21567033, -35.83838181), new LatLng(-7.25053016, -35.86164206),
                new LatLng(-7.24061826,  -35.92114704), new LatLng(-7.20815667, -35.915793), new LatLng(-7.20553193, -35.86928743), new LatLng(-7.27146965, -35.9133105),
                new LatLng(-7.22376693, -35.82471609), new LatLng(-7.27242779, -35.85447816), new LatLng(-7.21500736, -35.88014396)};
        String[] titles = {"", "", "", "", "", "", "", "", "", ""};

        for (int i = 0; i < 10; i++) {
            lista.add(new MarkerOptions()
                    .position(latlng[i])
                    .title(titles[i])
                    .anchor(0.0f, 1.0f)
                    .snippet("Lixo Hospitalar")
                    .icon(hospital_location));
        }

        MarkersDB.addMarkers(lista);

    }

    private static void createSelectiveMarkers() {
        BitmapDescriptor selective_location = BitmapDescriptorFactory.fromResource(R.drawable.ic_selective_location);

        List<MarkerOptions> lista = new ArrayList<>();
        LatLng[] latlng = {new LatLng(-7.16978414, -35.89384203), new LatLng(-7.23218182,  -35.92384687), new LatLng(-7.20008122,  -35.93238406), new LatLng(-7.19400661, -35.87302884),
                new LatLng(-7.17093352, -35.84672533), new LatLng(-7.23918341, -35.84851907), new LatLng(-7.23376194, -35.86227544), new LatLng(-7.167033, -35.88587832),
                new LatLng(-7.2690302, -35.86906695), new LatLng(-7.20256595,  -35.82259734)};
        String[] titles = {"", "", "", "", "", "", "", "", "", ""};

        for (int i = 0; i < 10; i++) {
            lista.add(new MarkerOptions()
                    .position(latlng[i])
                    .title(titles[i])
                    .anchor(0.0f, 1.0f)
                    .snippet("Coleta Seletiva")
                    .icon(selective_location));
        }

        MarkersDB.addMarkers(lista);
    }
}
