package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeClasico;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcialEstricto;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.estados.EstadoPregunta;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;

public class MultipleChoice extends Pregunta {

    public static MultipleChoice Clasico(String titulo) {
        return new MultipleChoice(
                titulo,
                "Multiple Choice Clasico",
                new SinPenalidad(new CalculadorPuntajeClasico())
        );
    }

    public static MultipleChoice ConPenalidad(String titulo) {
        return new MultipleChoice(
                titulo,
                "Multiple Choice Con Penalidad",
                new ConPenalidad(new CalculadorPuntajeParcial())
        );
    }

    public static MultipleChoice Parcial(String titulo) {
        return new MultipleChoice(
                titulo,
                "Multiple Choice Parcial",
                new SinPenalidad(new CalculadorPuntajeParcialEstricto())
        );
    }

    private MultipleChoice(String titulo, String tipo, EstadoPregunta estado) {
        super(titulo, tipo, estado);
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
}
