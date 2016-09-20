package com.descarteaqui.descarteaqui.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.controllers.PetitionController;
import com.descarteaqui.descarteaqui.model.Petition;
import com.github.clans.fab.FloatingActionButton;

/**
 * Created by Gabriel on 07/09/2016.
 */
public class SendPetitionFragment extends Fragment {

    private TextView senderEmail;
    private Spinner citySpinner;
    private EditText districtField;
    private EditText streetField;
    private EditText justificationField;
    private FloatingActionButton floatbuttonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_send, container, false);

        districtField = (EditText) rootView.findViewById(R.id.district_field);
        streetField = (EditText) rootView.findViewById(R.id.street_field);
        justificationField = (EditText) rootView.findViewById(R.id.justification_field);
        floatbuttonSend = (FloatingActionButton) rootView.findViewById(R.id.send_button);
        citySpinner = (Spinner) rootView.findViewById(R.id.city_spinner);
        senderEmail = (TextView) rootView.findViewById(R.id.sender_email);
        senderEmail.setSelected(true);

        floatbuttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (validateFields()){
                    Petition petition = new Petition(PetitionController.getLastID(getActivity()), streetField.getText().toString(),
                            districtField.getText().toString(),justificationField.getText().toString(), "asd");

                    PetitionController.createPetition(getActivity(), petition);

                    Toast.makeText(getActivity(), "Petição enviada com sucesso! :)", Toast.LENGTH_SHORT).show();

                    //Load the Petitions Fragment
                    FragmentManager fm = getFragmentManager();
                    Fragment fragment = new PetitionsFragment();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();

                } else {
                    Toast.makeText(getActivity(), "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        floatbuttonSend.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Enviar petição.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        return rootView;
    }

    private boolean validateFields(){
        if (streetField.getText().toString().isEmpty() ||
                districtField.getText().toString().isEmpty() ||
                    justificationField.getText().toString().isEmpty()){
            return false;
        } else {
            return true;
        }
    }

}
