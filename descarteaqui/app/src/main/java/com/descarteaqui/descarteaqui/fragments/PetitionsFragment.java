package com.descarteaqui.descarteaqui.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;

public class PetitionsFragment extends Fragment {

    private TextView sendPetition;
    private TextView ratePetitions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_petitions, container, false);

        sendPetition = (TextView) rootView.findViewById(R.id.send_petition);
        ratePetitions = (TextView) rootView.findViewById(R.id.rate_petition);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                optionClick(v);
            }
        };

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
