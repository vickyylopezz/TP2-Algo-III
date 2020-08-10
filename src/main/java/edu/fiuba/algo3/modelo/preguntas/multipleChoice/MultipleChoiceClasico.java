package edu.fiuba.algo3.modelo.preguntas.multipleChoice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
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

        Opcion opcion = new OpcionClasica(titulo, this.puntajeIncorrecto());
        this.opciones.add(opcion);
    }

    public void agregarOpcionCorrecta(String titulo) throws PreguntaError {
        if (this.opciones.size() == 5) {
            throw new CantidadMaximaDeOpcionesError();
        }

        Opcion opcion = new OpcionClasica(titulo, this.puntajeCorrecto());
        this.opciones.add(opcion);
    }

    @Override
    public void extraerOpciones(JsonObject object) throws PreguntaError {
        JsonArray opcionesCorrectas = object.getAsJsonArray("opcionesCorrectas");
        if (opcionesCorrectas == null) { return; /* EXCEPCION */ }
        JsonArray opcionesIncorrectas = object.getAsJsonArray("opcionesIncorrectas");
        if (opcionesIncorrectas == null) { return; /* LANZAR EXCEPCION */ }

        for (JsonElement opcion: opcionesCorrectas){
            agregarOpcionCorrecta(opcion.getAsString());
        }
        for (JsonElement opcion: opcionesIncorrectas){
            agregarOpcionIncorrecta(opcion.getAsString());
        }
    }
}
