package com.descarteaqui.descarteaqui.fragments;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;

public class PetitionsFragment extends Fragment {

    private TextView sendPetition;
    private TextView ratePetitions;
    private ImageView infoPetition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_petitions, container, false);

        sendPetition = (TextView) rootView.findViewById(R.id.send_petition);
        ratePetitions = (TextView) rootView.findViewById(R.id.rate_petition);
        infoPetition = (ImageView) rootView.findViewById(R.id.info_icon);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                optionClick(v);
            }
        };


        infoPetition.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vi) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("");

                alertDialogBuilder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        infoPetition.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Mais informações sobre as petições.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        sendPetition.setOnClickListener(listener);
        ratePetitions.setOnClickListener(listener);

        return rootView;
    }

    private void optionClick(View v){

        FragmentManager fm = getFragmentManager();
        Fragment fragment = null;

        int id = v.getId();
        if (id == R.id.send_petition)
            fragment = new SendPetitionFragment();
        else if (id == R.id.rate_petition)
            fragment = new RatePetitionsFragment();

        if (fragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
