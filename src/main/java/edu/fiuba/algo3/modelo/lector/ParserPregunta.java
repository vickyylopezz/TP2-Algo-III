package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;

public interface ParserPregunta {
    Pregunta parsear(JsonObject objeto) throws PreguntaError, LectorFormatoDePreguntaError;
}
