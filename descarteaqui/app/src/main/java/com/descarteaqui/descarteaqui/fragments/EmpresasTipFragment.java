package com.descarteaqui.descarteaqui.fragments;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;

public class EmpresasTipFragment extends Fragment {

    View rootView;
    ExpandableListView listViewEmpresas;
    private String[] groups;
    private String[][] children;


    public EmpresasTipFragment() {}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setItems();
    }

    private void setItems() {
        groups = new String[] { "EcoEntulhos", "SOS Entulhos", "Contramare", "Depet Reciclagem",
                "Senai - Usina Beneficiadora de Resíduos Sólidos", "Colégio Redentorista"};

        children = new String [][] {

                {"Endereço: Liberdade, Campina Grande - PB", "Telefone: (83) 3055-2224",
                        "Site: http://www.ecoentulho.comunidades.net/",
                        "Horário de Funcionamento: Seg-Sex: 7-17h, Sab: 7-12h",
                        "Informações: Atua na disponibilização de caçambas estacionárias, coleta e destinação de resíduos sólidos não perigosos."},

                {"Endereço: R. Carlos Alberto Souza, 608 - Bodocongó, Campina Grande - PB, 58109-155",
                        "Telefone: (83) 3333-1395", "Site: Não possui", "Horário de Funcionamento: Ter-Qui: 7:30-18h",
                        "Informações: Coleta de Entulho da Construção Civil."},

                {"Endereço: R. Santa Rita, 486, Campina Grande - PB, 58416-240",
                        "Telefone: (83) 98700-1416", "Site: http://cotramare.org/",
                        "Horário de Funcionamento: Seg-Sex: 8:30-16h",
                        "Informações: Trabalham coletando, separando, prensando, enfardando e comercializando os resíduos recicláveis."},

                {"Endereço: R. Min. Dílson Funaro, 10, Campina Grande - PB, 58418-042",
                        "Telefone: (83) 3335-4297", "Site: http://www.depet.com.br/",
                        "Horário de Funcionamento: informar-se sobre via telefone.",
                        "Informações: Atuam na moagem de Garrafas Pet , retirada de Resíduos Industriais e Projeto para os Resíduos Industriais."},

                {"Endereço: Avenida Assis Chateaubriand - Distrito Industrial, Campina Grande - PB, 58105-421",
                        "Telefone: (83) 3321-8488", "Site: Não possui", "Horário de Funcionamento: agendar via telefone.",
                        "Informações: A empresa Mundial tech desenvolveu a Usina Beneficiadora de Resíduos Sólidos Urbanos (UBRS)," +
                                " que transforma os resíduos sólidos deixados pelo homem em um material útil e ecologicamente correto."},

                {"Endereço: Av. Dr. Francisco Pinto, 317 - Cidade Universitária, Campina Grande - PB, 58429-350",
                        "Telefone: (83) 3333-1331", "Site: http://www.redentorista.org.br/",
                        "Horário de Funcionamento: Seg-Sex: 7-22h, Sáb: 7-18h.",
                        "Informações: Projeto ETERECICLA reaproveita materiais eletrônicos obsoletos ou que " +
                                "apresentaram alguma espécie de defeito. Aqueles que não podem ser consertados tem suas " +
                                "peças todas separadas e logo depois são enviadas à  uma empresa de reciclagem. Os equipamentos " +
                                "recuperados são doados ou emprestados a pessoas carentes, garantindo assim o seu acesso à tecnologia. "}
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.empresas_tip, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewEmpresas = (ExpandableListView) view.findViewById(R.id.empresasListView);
        listViewEmpresas.setAdapter(new ExpandableListAdapter(groups, children));
        listViewEmpresas.setGroupIndicator(null);

    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.empresas_tip, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.empresasListItem);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            String headerTitle = (String) getGroup(groupPosition);

            if (convertView == null) {
                convertView = inf.inflate(R.layout.empresas_tip, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.empresasListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            TextView header_text = (TextView) convertView.findViewById(R.id.empresasListHeader);
            header_text.setText(headerTitle);
            holder.text.setText(getGroup(groupPosition).toString());

            if (isExpanded) {
                header_text.setTypeface(null, Typeface.BOLD);
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        R.drawable.ic_expand_less_black_24dp, 0);
            } else {
                // If group is not expanded then change the text back into normal
                // and change the icon

                header_text.setTypeface(null, Typeface.NORMAL);
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        R.drawable.ic_expand_more_black_24dp, 0);
            }

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }

}
