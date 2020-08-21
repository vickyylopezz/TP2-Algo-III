package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.parsers.ParserOrderedChoice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserOrderedChoiceTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserOrderedChoice parser = new ParserOrderedChoice();

        String orderedChoiceJson = "{\n" +
                "  \"pregunta\":\"Ordenar Crecrientemente\",\n" +
                "  \"orden\":[\"1\", \"2\", \"3\", \"4\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(orderedChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonTipoInvalidoLanzaFormatoInvalidoError() {
        ParserOrderedChoice parser = new ParserOrderedChoice();

        String orderedChoiceJson = "{\n" +
                "  \"tipo\":\"cualquiera\",\n" +
                "  \"pregunta\":\"Ordenar Crecrientemente\",\n" +
                "  \"orden\":[\"1\", \"2\", \"3\", \"4\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(orderedChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinPreguntaLanzaFormatoInvalidoError() {
        ParserOrderedChoice parser = new ParserOrderedChoice();

        String orderedChoiceJson = "{\n" +
                "  \"tipo\":\"Ordered\",\n" +
                "  \"orden\":[\"1\", \"2\", \"3\", \"4\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(orderedChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinArrayOrdenLanzaFormatoInvalidoError() {
        ParserOrderedChoice parser = new ParserOrderedChoice();

        String orderedChoiceJson = "{\n" +
                "  \"tipo\":\"Ordered\",\n" +
                "  \"pregunta\":\"Ordenar Crecrientemente\"\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(orderedChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesCorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserOrderedChoice parser = new ParserOrderedChoice();

        String orderedChoiceJson = "{\n" +
                "  \"tipo\":\"Ordered\",\n" +
                "  \"pregunta\":\"Ordenar Crecrientemente\",\n" +
                "  \"orden\":5\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(orderedChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws PreguntaError, LectorFormatoDePreguntaError {
        ParserOrderedChoice parser = new ParserOrderedChoice();

        String orderedChoiceJson = "{\n" +
                "  \"tipo\":\"Ordered\",\n" +
                "  \"pregunta\":\"Ordenar Crecrientemente\",\n" +
                "  \"orden\":[\"1\", \"2\", \"3\", \"4\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(orderedChoiceJson).getAsJsonObject();

        Pregunta pregunta = parser.parsear(objetoPregunta);

        assertEquals("Ordenar Crecrientemente", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(4, pregunta.obtenerOpciones().size());
    }
}
