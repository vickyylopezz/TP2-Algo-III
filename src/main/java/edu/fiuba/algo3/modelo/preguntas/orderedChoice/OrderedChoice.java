package edu.fiuba.algo3.modelo.preguntas.orderedChoice;

import edu.fiuba.algo3.modelo.excepciones.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeOrdenado;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeClasico;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;

import java.util.ArrayList;

public class OrderedChoice extends Pregunta {

    public OrderedChoice(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeOrdenado()));
    }

    public void agregarOpcion(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new CantidadMaximaDeOpcionesError();
        }
        this.opciones.add(new OpcionClasica(opcionTitulo, new PuntoNulo()));
    }
}
