package edu.fiuba.algo3.modelo;

public class Opcion {

    private String titulo;
    private Integer valor;

    public Opcion(String titulo,Integer valor){
        this.titulo = titulo;
        this.valor = valor;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public Integer getValor(){
        return this.valor;
    }
}
