package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;

public class ParserOrderedChoice implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws PreguntaError {
        String tituloPregunta = objeto.get("pregunta").getAsString();
        OrderedChoice pregunta = new OrderedChoice(tituloPregunta);

        JsonArray orden = objeto.getAsJsonArray("orden");
        for (JsonElement opcion: orden){
            pregunta.agregarOpcion(opcion.getAsString());
        }

        return pregunta;
    }
}
