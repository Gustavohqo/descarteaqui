package com.descarteaqui.descarteaqui.model;

import java.util.HashSet;

/**
 * Created by gustavooliveira on 9/8/16.
 */
public class Peticao {
    private String cidade;
    private String bairro;
    private String rua;
    private String justificativa;
    private Usuario criador;
    private HashSet<Usuario> votosPositivos;
    private HashSet<Usuario> votosNegativos;

    /**
     * Construtor de Petição
     * @param cidade String não vazia com o nome da cidade
     * @param bairro String não vazia com o nome do bairro
     * @param rua String não vazia com o nome da rua
     * @param justificativa String não vazia com a justificativa
     * @param criador Objeto {@Usuario} não nulo representando o criador da petição
     */
    public Peticao(String cidade, String bairro, String rua, String justificativa, Usuario criador) {
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
        setJustificativa(justificativa);
        setCriador(criador);

        votosNegativos = new HashSet<Usuario>();
        votosPositivos = new HashSet<Usuario>();
    }

    /**
     * Adiciona usuario que votou positivo caso esse seja unico.
     * @param usuario objeto {@Usuario} não nulo.
     */
    public void addVotoPositivo(Usuario usuario){
        //TODO implmentar
    }

    /**
     * Adiciona usuario que votou negativo caso esse seja unico.
     * @param usuario objeto {@Usuario} não nulo.
     */
    public void addVotoNegativo(Usuario usuario){
        //TODO implmentar
    }

    /**
     * Remove um usuario da lista de votos Positivos caso este tenha votado positivo
     * @param usuario objeto {@Usuario} não nulo
     */
    public void removeVotoPositivo(Usuario usuario){
        //TODO implementar
    }

    /**
     * Remove um usuario da lista de votos Negativos caso este tenha votado negativo
     * @param usuario objeto {@Usuario} não nulo
     */
    public void removeVotoNegativo(Usuario usuario){
        //TODO implementar
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public Usuario getCriador() {
        return criador;
    }

    public HashSet<Usuario> getVotosPositivos() {
        return votosPositivos;
    }

    public HashSet<Usuario> getVotosNegativos() {
        return votosNegativos;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    private void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    private void setCriador(Usuario criador) {
        this.criador = criador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peticao peticao = (Peticao) o;

        if (!getCidade().equals(peticao.getCidade())) return false;
        if (!getBairro().equals(peticao.getBairro())) return false;
        if (!getRua().equals(peticao.getRua())) return false;
        return getCriador().equals(peticao.getCriador());

    }

    @Override
    public int hashCode() {
        int result = getCidade().hashCode();
        result = 31 * result + getBairro().hashCode();
        result = 31 * result + getRua().hashCode();
        result = 31 * result + getCriador().hashCode();
        return result;
    }
}
