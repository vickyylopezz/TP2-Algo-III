package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class CalculadorPuntajeClasico implements CalculadorPuntaje {

    /*
    * Calculador de puntaje clasico, para que se devuelve un punto
    * correcto todas las opciones tienen que ser correctas y la
    * cantidad de opciones correctas tiene que ser la misma que
    * las opciones correctas de la pregunta, en otro caso se
    * devuelve un punto incorrecto.
    * */

    @Override
    public Punto calcular(Pregunta pregunta, ArrayList<Opcion> opciones) throws PuntoError {
        for (Opcion opcion: opciones) {
            if (opcion.obtenerPunto().obtenerValor() != 1) return pregunta.puntajeIncorrecto();
        }
        if (pregunta.cantidadOpcionesCorrectas() == opciones.size()) {
            return pregunta.puntajeCorrecto();
        }
        return pregunta.puntajeIncorrecto();
    }
}
