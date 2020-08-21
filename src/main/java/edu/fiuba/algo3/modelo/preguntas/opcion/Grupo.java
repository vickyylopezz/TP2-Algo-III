package edu.fiuba.algo3.modelo.preguntas.opcion;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import java.util.ArrayList;

public class Grupo {

    private final String titulo;
    private final ArrayList<Opcion> opciones;

    public Grupo(String titulo) {
        this.titulo = titulo;
        this.opciones = new ArrayList<>();
    }

    public String obtenerTitulo(){ return this.titulo; }

    public ArrayList<Opcion> obtenerOpciones() { return this.opciones;}

    public void agregarOpcion(Opcion opcion) {
        opciones.add(opcion);
    }

}
