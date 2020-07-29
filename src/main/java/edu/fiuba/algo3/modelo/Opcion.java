package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.Composite.Punto;

public class Opcion {

    private String titulo;
    private Punto valor;

    public Opcion(String titulo, Punto punto){
        this.titulo = titulo;
        this.valor = punto;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public Integer getValor(){
        return this.valor.getValor();
    }
}
