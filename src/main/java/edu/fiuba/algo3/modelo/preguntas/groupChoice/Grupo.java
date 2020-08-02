package edu.fiuba.algo3.modelo.preguntas.groupChoice;

import edu.fiuba.algo3.modelo.juego.opcion.OpcionGroupChoice;


import java.util.ArrayList;

public class Grupo {
    private String titulo;
    private ArrayList<OpcionGroupChoice> opciones = new ArrayList<>();

    public Grupo(String titulo) {
        this.titulo = titulo;
    }

    public String obtenerTitulo(){return this.titulo; }

    public ArrayList<OpcionGroupChoice> obtenerOpciones() {return this.opciones;}

    public void agregarOpcion(OpcionGroupChoice opcion) {
        opciones.add(opcion);
    }

}
