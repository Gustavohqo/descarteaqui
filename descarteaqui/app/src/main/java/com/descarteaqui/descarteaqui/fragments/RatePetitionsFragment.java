package com.descarteaqui.descarteaqui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.adapter.PetitionAdapter;
import com.descarteaqui.descarteaqui.controllers.PetitionController;
import com.descarteaqui.descarteaqui.model.Petition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 07/09/2016.
 */
public class RatePetitionsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Petition> activeList = new ArrayList<>();;
    private TextView tabAllPetitions;
    private TextView tabMyPetitions;
    private TextView activeTab;
    private ImageView rateOKButton;
    private ImageView rateNGButton;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       rootView = inflater.inflate(R.layout.fragment_rate, container, false);

        View rateItemView = getActivity().getLayoutInflater().inflate(R.layout.rate_item, null);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_petitions);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayout);

        tabAllPetitions = (TextView) rootView.findViewById(R.id.tab_all_petitions);
        tabMyPetitions = (TextView) rootView.findViewById(R.id.tab_my_petitions);
        rateOKButton = (ImageView) rateItemView.findViewById(R.id.ok_rate);
        rateNGButton = (ImageView) rateItemView.findViewById(R.id.ng_rate);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                if (!activeTab.equals(v)) {
                    activeTab = (TextView) v;
                    changeTab();
                }
            }
        };

        tabAllPetitions.setOnClickListener(listener);
        tabMyPetitions.setOnClickListener(listener);

        this.activeTab = tabAllPetitions;

        changeTab();

        return rootView;
    }

    private void changeTab(){

        float value = 0, scaleMyPetitions = 0, scaleAllPetitions = 0;
        int alphaMyPetitions = 0, alphaAllPetitions = 0;

        if (activeTab.equals(tabAllPetitions)){
            activeList = allPetitionsList();
            scaleMyPetitions = 1;
            scaleAllPetitions = (float) 1.1;
            alphaAllPetitions = 200;
            alphaMyPetitions = 70;
            value = -10000;
        } else if (activeTab.equals(tabMyPetitions)){
            activeList = myPetitionsList();
            scaleMyPetitions = (float) 1.1;
            scaleAllPetitions = 1;
            alphaAllPetitions = 70;
            alphaMyPetitions = 200;
            value = 10000;
        }

        tabAllPetitions.animate().scaleX(scaleAllPetitions).scaleY(scaleAllPetitions);
        tabMyPetitions.animate().scaleX(scaleMyPetitions).scaleY(scaleMyPetitions);
        tabMyPetitions.setTextColor(tabMyPetitions.getTextColors().withAlpha(alphaMyPetitions));
        tabAllPetitions.setTextColor(tabAllPetitions.getTextColors().withAlpha(alphaAllPetitions));

        mRecyclerView.animate().setDuration(500).x(value);

        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.animate().setDuration(0).x(1);
            }
        }, 200);


        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                PetitionAdapter adapter = new PetitionAdapter(getActivity(), activeList);
                mRecyclerView.setAdapter(adapter);
            }
        }, 200);


    }

    private List<Petition> myPetitionsList(){
        activeList.clear();
        List<Petition> lista = PetitionController.getMyPetitions(getActivity(), "asd");
        activeList.addAll(lista);

        return activeList;
    }

    private List<Petition> allPetitionsList(){
        activeList.clear();
        List<Petition> lista = PetitionController.getAllPetitions(getActivity(), "asd");
        activeList.addAll(lista);

        return activeList;
    }

}