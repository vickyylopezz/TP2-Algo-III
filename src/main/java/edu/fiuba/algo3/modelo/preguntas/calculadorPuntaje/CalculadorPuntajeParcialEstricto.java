package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class CalculadorPuntajeParcialEstricto implements CalculadorPuntaje {
    @Override
    public Punto calcular(Pregunta pregunta, ArrayList<Opcion> opciones) throws PuntoError {
        Puntaje puntaje = new Puntaje();
        for (Opcion opcion: opciones) {
            if (opcion.obtenerPunto().obtenerValor() != 1) {
                return pregunta.puntajeIncorrecto();
            }
            puntaje.agregarPunto(opcion.obtenerPunto());
        }
        return puntaje;
    }
}
