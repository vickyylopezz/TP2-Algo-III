package edu.fiuba.algo3.modelo.lector.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.ParserPregunta;
import edu.fiuba.algo3.modelo.preguntas.OrderedChoice;

public class ParserOrderedChoice implements ParserPregunta {
    @Override
    public Pregunta parsear(JsonObject objeto) throws PreguntaError, LectorFormatoDePreguntaError {
        JsonElement tipoJson = objeto.get("tipo");
        if (tipoJson == null) {
            throw new LectorFormatoDePreguntaError("Tipo de pregunta no definido");
        }
        String tipo = tipoJson.getAsString();
        if (!tipo.equals("Ordered")) {
            throw new LectorFormatoDePreguntaError("Tipo pregunta invalido");
        }

        JsonElement preguntaJson = objeto.get("pregunta");
        if (preguntaJson == null) {
            throw new LectorFormatoDePreguntaError("Atributo 'pregunta' no definido");
        }
        String nombrePregunta = preguntaJson.getAsString();

        OrderedChoice pregunta = new OrderedChoice(nombrePregunta);

        JsonArray ordenOpcionesJson;
        try { ordenOpcionesJson = objeto.getAsJsonArray("orden"); }
        catch (ClassCastException e) {
            throw new LectorFormatoDePreguntaError("Array de opciones tipo incorrecto");
        }
        if (ordenOpcionesJson == null) {
            throw new LectorFormatoDePreguntaError("Array de opciones indefinido");
        }

        for (JsonElement opcion: ordenOpcionesJson){
            pregunta.agregarOpcion(opcion.getAsString());
        }

        return pregunta;
    }
}
