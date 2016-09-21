package com.descarteaqui.descarteaqui.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;

public class TipFragment extends Fragment {

    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;


    public TipFragment() {}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setItems();
    }

    private void setItems() {
        groups = new String[] { "Coleta Seletiva", "Lixos Orgânicos", "Eletrônicos", "Óleos",
                "Lixo Químico","Lixo Hospitalar"};

        String metais =  "Metais : os tipos mais conhecidos são o ferro, aço, cobre, estanho, chumbo, ouro e a prata. No quesito reciclagem, podemos citar o aço, muito comum " +
                "na forma de latinhas que podem ser reutilizadas para fabricação de novas latas, ou até mesmo novos utensílios, tais como arames, dobradiças, " +
                "entre outros. Uma característica interessante dessas latinhas de aço é que elas podem ser recicladas infinitamente, sem perder quaisquer " +
                "propriedade que lhe é fundamental. Outro metal que merece destaque é o alumínio, tendo em vista que o Brasil é o maior reciclador das latas " +
                "feitas com ele no mundo. Um ponto interessante é que a reciclagem de um quilo de alumínio economiza a extração de cerca de quatro quilos do " +
                "minério bauxita (matéria-prima usada em sua fabricação).";

        String coletaSeletiva = "Consiste na separação e recolhimento dos resíduos descartados de modo que atua diretamente na redução do lixo jogado na natureza. \n" +
                "O materiais que compõem a coleta seletiva são: papéis, metais, plásticos e vidros." ;

        String papel = "Papel : imprima sempre frente e verso,  pois isso reduz em 50% o consumo de folhas.";

        String plastico = "Plástico : tem como um dos principais representantes o PET que, quando reciclado, pode resultar em fibras de poliéster para produção de tecidos, " +
                "ou até embalagens para outros produtos que não sejam da indústria alimentícia. Além do mais, outro tipo de plástico muito utilizado são " +
                "as sacolas plásticas, que não são biodegradáveis, isto é, são difíceis de se decompor pela ação de agentes naturais, por isso é adequado " +
                "levar as próprias sacolas não plásticas ao fazer compras." ;

        String vidro =  "Vidro : é um material 100% reciclável, porque ele pode ser usado infinita vezes para fabricar outros vidros, além de ser preferível usar embalagens " +
                "de vidro retornáveis, pois isso reduz a necessidade de extração da matéria-prima e da fabricação. Além disso, no caso do descarte do vidro " +
                "quebrado, é interessante cortar uma garrafa e colocar os cacos dentro dela e lacrá-la, ou colocar em uma caixa de papelão e também lacrar.";

        String oleo ="No caso do óleo de cozinha, o mais adequado é colocá-lo em uma garrafa PET e enviar para a reciclagem. " +
                "Outra possibilidade seria fabricar sabão caseiro com ele. Além disso, para tratamentos em geral, utiliza-se a" +
                " técnica do rerrefino." +
                "Agora, uma curiosidade: um litro de óleo é capaz de acabar com o oxigênio de 1 milhão de litros de água.";

        String organicos ="Em geral, são provenientes de organismos animais ou vegetais. Nesse caso, uma abordagem de " +
                "reciclagem é a prática da compostagem, que possibilita transformar esses resíduos em adubo para o solo, " +
                "vasos de plantas etc. Porém, só alguns materiais podem ser usados na compostagem, como por exemplo: resto " +
                "de pães ou biscoitos, restos de grãos ou farinhas crus, esterco de animais herbívoros, grama e folhas secas," +
                " entre outros." +"Porém, não pode ser usados na compostagem materiais como leite, óleo, remédios, fezes " +
                "humanas e de animais doméstico." + "Além da compostagem, outro método de reciclagem é o aterramento, sendo " +
                "este mais indicado para locais em que não há recolhimento do lixo.";

        String hospitalar ="Seringas, bolsas de sangue etc não devem ser jogados no lixo comum, pelo fato de poderem causar alguma contaminação, principalmente aos catadores de lixo. \n" +
                "Nesse contexto, algumas alternativas são:" +
                "Incineração: não é tão adequada pelo fato de haver emissão de poluentes e as cinzas ainda preservarem a toxidade dos materiais incinerados;" +
                "Esterilização: é uma das alternativas mais válidas. Porém, pelo fato de ser muito cara, é pouco utilizada;" +
                "Deposição em valas assépticas: igualmente válida, porém é necessário espaço e fiscalização, o que limita o uso dessa técnica.";

        String quimico="No caso de remédios vencidos, impróprios para o consumo ou que não serão mais utilizados, é adequado levá-los para pontos de coleta de medicamentos, sendo estes," +
                "principalmente, em farmácias ou supermercados.";

        String eletronicos = "Computadores, impressoras, teclados, televisões, celulares e demais eletroeletrônicos devem ser" +
                " depositados em empresas especializadas, pois elas farão o processo de manufatura reversa(extração dos " +
                "materiais usados na fabricação do dispositivo), de modo a reaproveitar os materiais.";

        String pilhas = "Pilhas: não devem ser jogadas no lixo comum com outros materiais, pois possuem metais pesados que são" +
                " nocivos às pessoas e ao meio-ambiente." +
                " Nesse caso, é adequado enviá-las a uma empresa especializada.";

        String baterias = "Baterias: são feitas extrações dos componentes que a compõe de modo que possam ser utilizados " +
                "novamente no processo produtivo.";

        String descontoEnergisa = "No contexto de Campina Grande, a Energisa fornece descontos na conta de energia para quem " +
                "levar lixo eletrônico em uma unidade recebedora. Para mais informações, acesse o seguinte " +
                "site : http://g1.globo.com/pb/paraiba/noticia/2015/03/populacao-pode-trocar-lixo-eletronico-por-desconto-em-conta-de-luz-na-pb.html";


        children = new String [][] {
                { coletaSeletiva, papel, vidro, plastico, metais},
                {organicos},
                { eletronicos, pilhas, baterias, descontoEnergisa},
                {oleo},
                {quimico},
                {hospitalar}
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tip, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(groups, children));
        lv.setGroupIndicator(null);


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
                convertView = inf.inflate(R.layout.fragment_tip, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
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
                convertView = inf.inflate(R.layout.fragment_tip, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            TextView header_text = (TextView) convertView.findViewById(R.id.lblListHeader);
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
