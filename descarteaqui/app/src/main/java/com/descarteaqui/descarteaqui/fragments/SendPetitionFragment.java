package com.descarteaqui.descarteaqui.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.descarteaqui.descarteaqui.controllers.UserController;
import com.descarteaqui.descarteaqui.model.Petition;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

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
    private TextInputLayout streetInputLayout;
    private TextInputLayout districtInputLayout;
    private TextInputLayout justificationInputLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_send, container, false);

        districtField = (EditText) rootView.findViewById(R.id.district_field);
        streetField = (EditText) rootView.findViewById(R.id.street_field);
        justificationField = (EditText) rootView.findViewById(R.id.justification_field);
        floatbuttonSend = (FloatingActionButton) rootView.findViewById(R.id.send_button);
        citySpinner = (Spinner) rootView.findViewById(R.id.city_spinner);
        senderEmail = (TextView) rootView.findViewById(R.id.sender_email);
        senderEmail.setText(senderEmail.getText() + UserController.getCurrentUser(getActivity()));
        senderEmail.setSelected(true);

        floatbuttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (validateFields() && userCanCreate()){
                    Petition petition = new Petition(PetitionController.getLastID(getActivity()), streetField.getText().toString(),
                            districtField.getText().toString(),justificationField.getText().toString(), UserController.getCurrentUser(getActivity()));

                    PetitionController.createPetition(getActivity(), petition);

                    showProgressDialog();

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

    private boolean userCanCreate(){
        List<Petition> myPetitions = PetitionController.getMyPetitions(getActivity(), UserController.getCurrentUser(getActivity()));


        int districtCreateLimit = 0;

        for (int i = 0; i < myPetitions.size(); i++) {
            if (myPetitions.get(i).getDistrictName().toUpperCase().equals(districtField.getText().toString().toUpperCase())){
                districtCreateLimit++;
            }
        }

        if (districtCreateLimit > 0){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle("Não foi possível enviar a Petição.");
            alertDialogBuilder.setMessage("Você já estrapolou o limite de petições por bairro.");

            alertDialogBuilder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            FragmentManager fm = getFragmentManager();
            Fragment fragment = new PetitionsFragment();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return false;
        } else {
            return true;
        }
    }

    private boolean validateFields(){
        boolean fields_ok = true;

        if (streetField.getText().toString().isEmpty()){
            streetField.setError("Este campo não pode ficar vazio");;
            fields_ok = false;
        } if (districtField.getText().toString().isEmpty()) {
            districtField.setError("Este campo não pode ficar vazio");;
            fields_ok = false;
        } if (justificationField.getText().toString().isEmpty()){
            justificationField.setError("Este campo não pode ficar vazio");;
            fields_ok = false;
        }

        return fields_ok;
    }

    private void showProgressDialog(){
        final int TIME = 1*1500;
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Enviando a Petição.");
        dialog.setCancelable(false);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Petição enviada com sucesso!");
                alertDialogBuilder.setMessage("Você quer ajuda dos seus amigos? Compartilhe com eles! :)");

                alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        FragmentManager fm = getFragmentManager();
                        Fragment fragment = new PetitionsFragment();
                        fm.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();

                        shareSocialMedia(getView());
                    }
                });

                alertDialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        FragmentManager fm = getFragmentManager();
                        Fragment fragment = new PetitionsFragment();
                        fm.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }, TIME);
    }

    public void shareSocialMedia(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Baixe o DescarteAqui e venha me ajudar votando na minha petição para inserir um ponto de coleta/descarte aqui no " + districtField.getText()
                + ", na " + streetField.getText()+ ".");
        startActivity(Intent.createChooser(intent, "Compartilhar com"));
    }

}
