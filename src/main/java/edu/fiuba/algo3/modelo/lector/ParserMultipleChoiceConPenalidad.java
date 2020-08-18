package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceConPenalidad;

public class ParserMultipleChoiceConPenalidad implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws PreguntaError {
        String nombrePregunta = objeto.get("pregunta").getAsString();
        MultipleChoiceConPenalidad pregunta = new MultipleChoiceConPenalidad(nombrePregunta);

        JsonArray opcionesCorrectas = objeto.getAsJsonArray("opcionesCorrectas");
        JsonArray opcionesIncorrectas = objeto.getAsJsonArray("opcionesIncorrectas");

        for (JsonElement opcion: opcionesCorrectas){
            pregunta.agregarOpcionCorrecta(opcion.getAsString());
        }
        for (JsonElement opcion: opcionesIncorrectas){
            pregunta.agregarOpcionIncorrecta(opcion.getAsString());
        }

        return pregunta;
    }
}
