package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.parsers.ParserMultipleChoice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserMultipleChoiceConPenalidadTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonTipoInvalidoLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"caramelo\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinPreguntaLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinOpcionesCorrectasLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesCorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":true,\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinOpcionesIncorrectasLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesIncorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":false\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws PreguntaError, LectorFormatoDePreguntaError {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        Pregunta pregunta = parser.parsear(objetoPregunta);

        assertEquals("¿Es True/False con penalidad?", pregunta.obtenerTitulo());
        assertTrue(pregunta.conPenalidad());
        assertEquals(4, pregunta.obtenerOpciones().size());
    }

    @Test
    public void parserConCantidadDePreguntasCeroLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[],\n" +
                "  \"opcionesIncorrectas\":[]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserConCantidadDePreguntasUnoEnIncorrectaLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[],\n" +
                "  \"opcionesIncorrectas\":[\"Yes\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserConCantidadDePreguntasUnoEnCorrectaLanzaFormatoInvalidoError() {
        ParserMultipleChoice parser = new ParserMultipleChoice();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"Nope\"],\n" +
                "  \"opcionesIncorrectas\":[]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }
}
