package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoExacto;

import java.util.ArrayList;

public class CalculadorPuntajeParcialEstricto implements CalculadorPuntaje {
    @Override
    public Punto calcular(Pregunta pregunta, ArrayList<Opcion> opciones){
        PuntoExacto puntaje = new PuntoExacto();
        for (Opcion opcion: opciones) {
            if (opcion.obtenerPunto().obtenerValor() != 1) {
                return pregunta.puntajeIncorrecto();
            }
            puntaje.agregarValor(opcion.obtenerPunto());
        }
        return puntaje;
    }
}
