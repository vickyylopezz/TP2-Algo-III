package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.*;


import java.util.ArrayList;

public class Grupo {
    private String titulo;
    private ArrayList<OpcionGroupChoice> opciones = new ArrayList<>();

    public Grupo(String titulo) {
        this.titulo = titulo;
    }

    public void agregarOpcion(OpcionGroupChoice opcion) {
        opciones.add(opcion);
    }

}
