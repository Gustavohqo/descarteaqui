package com.descarteaqui.descarteaqui.controller;

import com.descarteaqui.descarteaqui.model.Peticao;
import com.descarteaqui.descarteaqui.model.Usuario;

import java.util.ArrayList;

/**
 * Created by gustavooliveira on 9/8/16.
 */
public class ControllerPeticoes {

    /**
     * Recupera uma única petição a partir de um id passado.
     * @param id_peticao
     *              String única que representa a a Petição desejada.
     * @return Objeto {@Peticao} tal que o id seja igual ao id passado pelo parametro
     */
    public Peticao getPeticao(String id_peticao){
        //TODO implementar
        return null;
    }

    /**
     * Lista todas as petições que existam no sistema
     * @return Uma lista contendo as petições existentes, ou uma lista vazia caso nao exista
     *      nenhuma petição cadastrada.
     */
    public ArrayList<Peticao> listarPeticoes() {
        //TODO implementar
        return null;
    }

    /**
     * Cria Petiçao a partir dos dados recebidos por parametro
     * @param Cidade Nome da cidade no qual deseja criar a petição
     * @param Bairro Nome do bairro referente ao local no qual deseja criar a petição
     * @param Rua Nome da rua no qual deseja criar a petição
     * @param justificativa  String contendo a justificativa
     * @return True caso a petiçao seja criada corretamente, false caso contrario.
     */
    public boolean criaPeticao (String Cidade, String Bairro, String Rua, String justificativa){
        //TODO implementar
        return false;
    }

    /**
     *
     * @param id_peticao
     * @param user
     * @return
     */
    public boolean votaEmPeticao (String id_peticao, Usuario user) {
        //TODO implementar
        return false;
    }
}
