package com.descarteaqui.descarteaqui.controllers;

import android.content.Context;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.database.TipsDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gabriel on 21/09/2016.
 */
public abstract class TipsController {

    private static TipsDB TipsDB;

    public static void createCEPs(Context ctx){
        addCEP(ctx, "58433-521", new int[]{1, 0, 0, 0, 0, 0, 0});
        addCEP(ctx, "58433-522", new int[]{1, 1, 0, 0, 0, 0, 0});
        addCEP(ctx, "58433-523", new int[]{1, 1, 1, 0, 0, 0, 0});
        addCEP(ctx, "58433-524", new int[]{1, 1, 1, 1, 0, 0, 0});
        addCEP(ctx, "58433-525", new int[]{1, 1, 1, 1, 1, 0, 0});
        addCEP(ctx, "58433-526", new int[]{1, 1, 1, 1, 1, 1, 0});
        addCEP(ctx, "58433-527", new int[]{1, 1, 1, 1, 1, 1, 1});
        addCEP(ctx, "58433-528", new int[]{1, 0, 1, 0, 1, 0, 1});
        addCEP(ctx, "58433-528", new int[]{0, 1, 0, 1, 0, 1, 0});
    }

    public static void addCEP(Context ctx, String CEP, int[] trashDays) {

        TipsDB = new TipsDB(ctx);

        TipsDB.addCEP(CEP, trashDays);

    }

    public static List<Boolean> searchCEP(Context ctx, String CEP){

        TipsDB = new TipsDB(ctx);

        return TipsDB.searchCEP(CEP);
    }

    public static List<String> getGroupsEmp(){

        List<String> groups = new ArrayList<>();
        groups.add("EcoEntulhos");
        groups.add("SOS Entulhos");
        groups.add("Contramare");
        groups.add("Depet Reciclagem");
        groups.add("Senai - Usina Beneficiadora de Resíduos Sólidos");
        groups.add("Colégio Redentorista");

        return groups;
    }

    public static List<String> getGroupsTip(){

        List<String> groups = new ArrayList<>();
        groups.add("Coleta Seletiva");
        groups.add("Lixos Orgânicos");
        groups.add("Eletrônicos");
        groups.add("Óleos");
        groups.add("Lixo Químico");
        groups.add("Lixo Hospitalar");

        return groups;

    }

    public static int getIcon(int empresaIndex){
        int[] icons = {R.drawable.default_emp, R.drawable.default_emp, R.drawable.default_emp, R.drawable.default_emp, R.drawable.default_emp, R.drawable.default_emp};

        return icons[empresaIndex];
    }

    public static HashMap<String, List<String>> getChildrenEmp(){

        HashMap<String, List<String>> children = new HashMap<>();
        List<String> auxList = new ArrayList<>();

        auxList.add("Liberdade, Campina Grande - PB");
        auxList.add("(83) 3055-2224");
        auxList.add("http://www.ecoentulho.comunidades.net/");
        auxList.add("Seg-Sex: 7-17h, Sab: 7-12h");
        auxList.add("Atua na disponibilização de caçambas estacionárias, coleta e destinação de resíduos sólidos não perigosos.");

        children.put(getGroupsEmp().get(0), auxList);

        auxList = new ArrayList<>();

        auxList.add("R. Santa Rita, 486, Campina Grande - PB, 58416-240");
        auxList.add("(83) 98700-1416");
        auxList.add("http://cotramare.org/");
        auxList.add("Seg-Sex: 8:30-16h");
        auxList.add("Trabalham coletando, separando, prensando, enfardando e comercializando os resíduos recicláveis.");

        children.put(getGroupsEmp().get(1), auxList);

        auxList = new ArrayList<>();

        auxList.add("R. Min. Dílson Funaro, 10, Campina Grande - PB, 58418-042");
        auxList.add("(83) 3335-4297");
        auxList.add("http://www.depet.com.br");
        auxList.add("Agendar via telefone.");
        auxList.add("Atuam na moagem de Garrafas Pet , retirada de Resíduos Industriais e Projeto para os Resíduos Industriais.");

        children.put(getGroupsEmp().get(2), auxList);

        auxList = new ArrayList<>();

        auxList.add("Avenida Assis Chateaubriand - Distrito Industrial, Campina Grande - PB, 58105-421");
        auxList.add("(83) 3321-8488");
        auxList.add("Não possui");
        auxList.add("Agendar via telefone.");
        auxList.add("A empresa Mundial tech desenvolveu a Usina Beneficiadora de Resíduos Sólidos Urbanos (UBRS)," +
                " que transforma os resíduos sólidos deixados pelo homem em um material útil e ecologicamente correto.");

        children.put(getGroupsEmp().get(3), auxList);

        auxList = new ArrayList<>();

        auxList.add("Av. Dr. Francisco Pinto, 317 - Cidade Universitária, Campina Grande - PB, 58429-350");
        auxList.add("(83) 3333-1331");
        auxList.add("http://www.redentorista.org.br/");
        auxList.add("Seg-Sex: 7-22h, Sáb: 7-18h.");
        auxList.add("Projeto ETERECICLA reaproveita materiais eletrônicos obsoletos ou que " +
                "apresentaram alguma espécie de defeito. Aqueles que não podem ser consertados tem suas " +
                "peças todas separadas e logo depois são enviadas à  uma empresa de reciclagem. Os equipamentos " +
                "recuperados são doados ou emprestados a pessoas carentes, garantindo assim o seu acesso à tecnologia. ");

        children.put(getGroupsEmp().get(4), auxList);

        return children;
    }

    public static HashMap<String, List<String>> getChildrenTips(){

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


        HashMap<String, List<String>> children = new HashMap<>();
        List<String> auxList = new ArrayList<>();

        auxList.add(coletaSeletiva);
        auxList.add(papel);
        auxList.add(vidro);
        auxList.add(plastico);
        auxList.add(metais);
        children.put(getGroupsTip().get(0), auxList);

        auxList = new ArrayList<>();

        auxList.add(organicos);
        children.put(getGroupsTip().get(1), auxList);

        auxList = new ArrayList<>();

        auxList.add(eletronicos);
        auxList.add(pilhas);
        auxList.add(baterias);
        auxList.add(descontoEnergisa);
        children.put(getGroupsTip().get(2), auxList);

        auxList = new ArrayList<>();

        auxList.add(oleo);
        children.put(getGroupsTip().get(3), auxList);

        auxList = new ArrayList<>();

        auxList.add(quimico);
        children.put(getGroupsTip().get(4), auxList);

        auxList = new ArrayList<>();

        auxList.add(hospitalar);
        children.put(getGroupsTip().get(5), auxList);

        return children;
    }

}
