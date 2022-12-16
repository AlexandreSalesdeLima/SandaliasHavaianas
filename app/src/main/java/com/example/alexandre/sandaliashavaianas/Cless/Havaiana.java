package com.example.alexandre.sandaliashavaianas.Cless;

import java.io.Serializable;

/**
 * Created by Wilma on 23/10/2015.
 */
public class Havaiana implements Serializable {

    public long id;
    public String nome;
    public String gtafabricante;
    public String valor;
    public String ref;
    public String capa;


    //Construtor para detalhe
    public Havaiana(String nome, String gtafabricante ,String valor, String ref,String capa) {

        this.nome = nome;
        this.gtafabricante = gtafabricante;
        this.valor = valor;
        this.ref = ref;
        this.capa = capa;

    }/*
    //Construtor para banco
     public Cardapio(long id, String nome, String ingrediente, String imagem) {
        this( nome, ingrediente, imagem);
        this.id=id;
    }*/

    //Construtor de teste da lista
    public Havaiana(String nome, String gtafabricante){
        this.nome=nome;
        this.gtafabricante = gtafabricante;
    }
    //Construtor default
    public Havaiana(){}
    @Override
    public String toString() {
        return nome;
    }
}
