package com.descarteaqui.descarteaqui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.adapter.PetitionAdapter;
import com.descarteaqui.descarteaqui.core.Petition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 07/09/2016.
 */
public class RatePetitionsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Petition> listPetitions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rate, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_petitions);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayout);


        listPetitions = new ArrayList<>();
        addItems(10);
        PetitionAdapter adapter = new PetitionAdapter(getActivity(), listPetitions);
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    private void addItems(int qtd){
        for (int i = 0; i < qtd; i++) {
            listPetitions.add(new Petition("Rua do " + i, "Bairro do " + i, "Pq sim ne " + i, "Eu sou " + i));
        }
    }

}
