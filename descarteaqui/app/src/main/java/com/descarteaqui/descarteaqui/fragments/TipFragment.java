package com.descarteaqui.descarteaqui.fragments;


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

        String papel = "Papel : possui grande potencial de reciclagem. Porém, só alguns tipos podem ser reciclados, tais como: " +
                "jornais e revistas, fotocópias e de fax, folhas de caderno, envelopes, provas, caixas em geral, rascunhos, " +
                "aparas de papel e cartazes velhos. Ademais, uma dica importante: imprima sempre frente e verso,  pois isso reduz em 50% o consumo de folhas.";

        String plastico = "Plástico : tem como um dos principais representantes o PET que, quando reciclado, pode resultar em fibras de poliéster para produção de tecidos, " +
                "ou até embalagens para outros produtos que não sejam da indústria alimentícia. Além do mais, outro tipo de plástico muito utilizado são " +
                "as sacolas plásticas, que não são biodegradáveis, isto é, são difíceis de se decompor pela ação de agentes naturais, por isso é adequado " +
                "levar as próprias sacolas não plásticas ao fazer compras." ;

        String vidro =  "Vidro : é um material 100% reciclável, porque ele pode ser usado infinita vezes para fabricar outros vidros, além de ser preferível usar embalagens " +
                "de vidro retornáveis, pois isso reduz a necessidade de extração da matéria-prima e da fabricação. Além disso, no caso do descarte do vidro " +
                "quebrado, é interessante cortar uma garrafa e colocar os cacos dentro dela e lacrá-la, ou colocar em uma caixa de papelão e também lacrar.";

        children = new String [][] {
                { coletaSeletiva, papel, vidro, plastico, metais},
                { "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of comes from a line in section 1.10.32." },
                { "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)." },
                { "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc." }
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
        //lv.setGroupIndicator(null);


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

            if (convertView == null) {
                convertView = inf.inflate(R.layout.fragment_tip, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());
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
