package com.descarteaqui.descarteaqui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;

/**
 * Created by Gabriel on 07/09/2016.
 */
public class RatePetitionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rate, container, false);

        return rootView;
    }

}
