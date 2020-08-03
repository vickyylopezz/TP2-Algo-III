package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class CalculadorPuntajeParcial implements CalculadorPuntaje {

    /*
     * Calculador de puntaje parcial, se agrega un punto correcto por
     * cada opcion correcta y un incorrecto por cada opcion incorrecta
     * en el puntaje, opciones correctas devuelve si selecciono todas
     * las opciones correctas.
     * */

    @Override
    public Punto calcular(Pregunta pregunta, ArrayList<Opcion> opciones) {
        Puntaje puntaje = new Puntaje();
        for (Opcion opcion: opciones) {
            puntaje.agregarPunto(opcion.obtenerPunto());
        }
        return puntaje;
    }
}
