package edu.fiuba.algo3.modelo.preguntas.orderedChoice;

import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeOrdenado;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;

public class OrderedChoice extends Pregunta {

    public OrderedChoice(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeOrdenado()));
    }

    public void agregarOpcion(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new CantidadMaximaDeOpcionesError();
        }
        this.opciones.add(new Opcion(opcionTitulo, new PuntoNulo()));
    }
}
