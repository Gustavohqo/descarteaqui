package com.descarteaqui.descarteaqui.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.adapter.TipAdapter;
import com.descarteaqui.descarteaqui.controllers.TipsController;

import java.util.HashMap;
import java.util.List;

public class TipFragment extends Fragment {

    private int lastExpandedPosition = -1;
    private View rootView;
    private ExpandableListView expandableListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listData;
    private TextView activeTab;
    private TextView tabEmpresas;
    private TextView tabDicas;
    private TextView tabCEP;
    private EditText searchField;
    private RelativeLayout cepTable;
    private TextView addresField;

    private void setItems() {

        if (activeTab.equals(tabDicas)){
            listGroup = TipsController.getGroupsTip();
            listData = TipsController.getChildrenTips();
        } else if (activeTab.equals(tabEmpresas)) {
            listGroup = TipsController.getGroupsEmp();
            listData = TipsController.getChildrenEmp();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tip, container, false);

        tabDicas = (TextView) rootView.findViewById(R.id.tab_dicas);
        tabCEP = (TextView) rootView.findViewById(R.id.tab_cep);
        tabEmpresas = (TextView) rootView.findViewById(R.id.tab_empresas);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandable_list_view);
        cepTable = (RelativeLayout) rootView.findViewById(R.id.cep_layout);
        searchField = (EditText) rootView.findViewById(R.id.search_field);
        addresField = (TextView) rootView.findViewById(R.id.addres_field);
        addresField.setSelected(true);

        TextWatcher textWatcher = new TextWatcher() {

            boolean yeah = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                yeah = searchField.getText().toString().contains("-");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5 && !yeah){
                    if (!searchField.getText().toString().contains("-")) {
                        searchField.setText(searchField.getText() + "-");
                        searchField.setSelection(searchField.getText().length());
                    }
                }

                if (s.length() == 9) {

                    final List<String> ceps = TipsController.searchCEP(getActivity(), searchField.getText().toString());
                    final String address = TipsController.getAddress(getActivity(), searchField.getText().toString()).toUpperCase();
                    addresField.animate().scaleX(0).scaleY(0).setDuration(100);

                    rootView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (address.equals("")){
                                addresField.setText("NENHUM ENDEREÇO ENCONTRADO :(");
                            } else {
                                addresField.setText(address);
                            }
                            addresField.animate().scaleX(1).scaleY(1).setDuration(100);;
                        }
                    }, 300);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                yeah = searchField.getText().toString().contains("-");
            }
        };

        searchField.addTextChangedListener(textWatcher);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                if (!activeTab.equals(v)) {
                    activeTab = (TextView) v;
                    changeTab();
                    setItems();
                }
            }
        };

        tabEmpresas.setOnClickListener(listener);
        tabCEP.setOnClickListener(listener);
        tabDicas.setOnClickListener(listener);

        activeTab = tabDicas;

        setItems();

        changeTab();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        expandableListView.setGroupIndicator(null);

        TipsController.createCEPs(getActivity());

        return rootView;
    }

    private void changeTab(){

        float value = 0, scaleDicas = 0, scaleEmp = 0, scaleCEP = 0;
        int alphaDicas = 0 , alphaEmp = 0, alphaCEP = 0;

        if (activeTab.equals(tabDicas)){
            scaleCEP = 1;
            scaleEmp = 1;
            scaleDicas = (float) 1.1;

            alphaDicas = 200;
            alphaCEP = 70;
            alphaEmp = 70;

            value = -10000;
        } else if (activeTab.equals(tabEmpresas)){
            scaleEmp = (float) 1.1;
            scaleCEP = 1;
            scaleDicas = 1;

            alphaCEP = 70;
            alphaDicas = 70;
            alphaEmp = 200;

            value = -10000;
        } else if (activeTab.equals(tabCEP)){
            scaleCEP = (float) 1.1;
            scaleEmp = 1;
            scaleDicas = 1;

            alphaEmp = 70;
            alphaDicas = 70;
            alphaCEP = 200;

            value = 10000;
        }

        tabDicas.animate().scaleX(scaleDicas).scaleY(scaleDicas);
        tabCEP.animate().scaleX(scaleCEP).scaleY(scaleCEP);
        tabEmpresas.animate().scaleX(scaleEmp).scaleY(scaleEmp);
        tabDicas.setTextColor(tabDicas.getTextColors().withAlpha(alphaDicas));
        tabCEP.setTextColor(tabCEP.getTextColors().withAlpha(alphaCEP));
        tabEmpresas.setTextColor(tabEmpresas.getTextColors().withAlpha(alphaEmp));

        expandableListView.animate().setDuration(500).x(value);

        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                expandableListView.animate().setDuration(0).x(1);
            }
        }, 200);


        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                String type = "";
                int CEP_VISIBILITY = View.INVISIBLE;
                int EXP_VISIBILITY = View.VISIBLE;
                if (activeTab.equals(tabEmpresas)){
                    type = "Empresas";
                    TipAdapter adapter = new TipAdapter(getActivity(), listGroup, listData, type);
                    expandableListView.setAdapter(adapter);
                    CEP_VISIBILITY = View.INVISIBLE;
                    EXP_VISIBILITY = View.VISIBLE;
                } else if (activeTab.equals(tabDicas)) {
                    type = "EcoDicas";
                    TipAdapter adapter = new TipAdapter(getActivity(), listGroup, listData, type);
                    expandableListView.setAdapter(adapter);
                    CEP_VISIBILITY = View.INVISIBLE;
                    EXP_VISIBILITY = View.VISIBLE;
                } else if (activeTab.equals(tabCEP)){
                    CEP_VISIBILITY = View.VISIBLE;
                    EXP_VISIBILITY = View.INVISIBLE;

                }

                expandableListView.setVisibility(EXP_VISIBILITY);
                cepTable.setVisibility(CEP_VISIBILITY);

            }
        }, 200);


    }


}
