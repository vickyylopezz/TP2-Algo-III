package edu.fiuba.algo3.modelo.preguntas.opcion;

import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.util.punto.Punto;

public class Opcion {

    private final String titulo;
    private final Punto punto;
    private final Grupo grupo;

    public Opcion(String titulo, Punto punto, Grupo grupo){
        this.titulo = titulo;
        this.punto = punto;
        this.grupo = grupo;
    }

    public Opcion(String titulo, Punto punto) {
        this.titulo = titulo;
        this.punto = punto;
        this.grupo = null;
    }

    public String obtenerTitulo(){ return this.titulo; }

    public Punto obtenerPunto(){ return this.punto; }

    public Grupo agrupacion() { return this.grupo; }

}
