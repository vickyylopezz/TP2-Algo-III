package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.estados.ConPenalidad;

public class MultipleChoiceConPenalidad extends Pregunta {

    public MultipleChoiceConPenalidad(String titulo) {
        super(titulo, new ConPenalidad(new CalculadorPuntajeParcial()));
    }

    public void agregarOpcionCorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new CantidadMaximaDeOpcionesError();
        }
        Opcion opcion = new Opcion(opcionTitulo, this.puntajeCorrecto());
        this.opciones.add(opcion);
    }

    public void agregarOpcionIncorrecta(String opcionTitulo) throws PreguntaError {
        if (this.opciones.size() == 5){
            throw new CantidadMaximaDeOpcionesError();
        }
        Opcion opcion = new Opcion(opcionTitulo, this.puntajeIncorrecto());
        this.opciones.add(opcion);
    }

    @Override
    public String obtenerTipo() { return "Multiple Choice Con Penalidad"; }
}
