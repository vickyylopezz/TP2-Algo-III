package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeClasico;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;

public class MultipleChoiceClasico extends Pregunta {

    public MultipleChoiceClasico(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeClasico()));
    }

    public void agregarOpcionIncorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new CantidadMaximaDeOpcionesError();
        }

        Opcion opcion = new Opcion(titulo, this.puntajeIncorrecto());
        this.opciones.add(opcion);
    }

    public void agregarOpcionCorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new CantidadMaximaDeOpcionesError();
        }

        Opcion opcion = new Opcion(titulo, this.puntajeCorrecto());
        this.opciones.add(opcion);
    }

    @Override
    public String obtenerTipo() { return "Multiple Choice Clasico"; }
}
