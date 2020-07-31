package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.util.punto.Punto;

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

    public Punto getValor(){ return this.valor; }
}
