package com.descarteaqui.descarteaqui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MapFragment extends Fragment {

    FloatingActionMenu fam;
    FloatingActionButton fab1, fab2, fab3, fab4, fab5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        fab1 = (FloatingActionButton) rootView.findViewById(R.id.fab_battery);
        fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab_oil);
        fab3 = (FloatingActionButton) rootView.findViewById(R.id.fab_chemistry);

        // listener to use click handler on fragment items
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v){
                floatButtonClick(v);
            }
        };

        // set the listener to work like activity methods
        fab1.setOnClickListener(listener);
        fab2.setOnClickListener(listener);
        fab3.setOnClickListener(listener);

        return rootView;
    }

    public void floatButtonClick(View v) {

        switch (v.getId()){
            case R.id.fab_battery:
                Toast.makeText(getContext(),"Battery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_oil:
                Toast.makeText(getContext(),"Oil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_chemistry:
                Toast.makeText(getContext(),"Chemistry", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

}
