package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class CalculadorPuntajeOrdenado implements CalculadorPuntaje {

    @Override
    public Punto calcular(Pregunta pregunta, ArrayList<Opcion> opciones) {
        ArrayList<Opcion> opcionesPregunta = pregunta.obtenerOpciones();

        if (opciones.size() != opcionesPregunta.size()) {
            return pregunta.puntajeIncorrecto();
        }

        for (Opcion opcion: opciones) {
            if (opciones.indexOf(opcion) != opcionesPregunta.indexOf(opcion)) {
                return pregunta.puntajeIncorrecto();
            }
        }

        return pregunta.puntajeCorrecto();
    }
}
