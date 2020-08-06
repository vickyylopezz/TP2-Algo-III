package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import edu.fiuba.algo3.modelo.excepciones.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcialEstricto;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;
import edu.fiuba.algo3.modelo.util.punto.*;

import java.util.ArrayList;

public class MultipleChoiceParcial extends Pregunta {

    public MultipleChoiceParcial(String titulo) {
        super(titulo, new SinPenalidad(new CalculadorPuntajeParcialEstricto()));
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new CantidadMaximaDeOpcionesError();
        }
        Opcion opcion = new OpcionClasica(opcionTitulo, this.puntajeCorrecto());
        this.opciones.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new CantidadMaximaDeOpcionesError();
        }
        Opcion opcion = new OpcionClasica(opcionTitulo, this.puntajeIncorrecto());
        this.opciones.add(opcion);
    }
}
