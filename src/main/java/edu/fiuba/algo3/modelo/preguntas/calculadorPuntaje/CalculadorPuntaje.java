package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public interface CalculadorPuntaje {
    Punto calcular(Pregunta pregunta, ArrayList<Opcion> opciones) throws PuntoError;
}
