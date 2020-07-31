package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.util.punto.Punto;

public class Opcion {

    private final String titulo;
    private final Punto punto;

    public Opcion(String titulo, Punto punto){
        this.titulo = titulo;
        this.punto = punto;
    }

    public String obtenerTitulo(){ return this.titulo; }

    public Punto obtenerPunto(){ return this.punto; }
}
