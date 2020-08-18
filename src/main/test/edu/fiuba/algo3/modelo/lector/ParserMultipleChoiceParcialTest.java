package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserMultipleChoiceParcialTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

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
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

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
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinOpcionesCorrectasLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesCorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":true,\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinOpcionesIncorrectasLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesIncorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":false\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws PreguntaError, LectorFormatoDePreguntaError {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        Pregunta pregunta = parser.parsear(objetoPregunta);

        assertEquals("¿Es True/False con penalidad?", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(4, pregunta.obtenerOpciones().size());
    }

    @Test
    public void parserConCantidadDePreguntasCeroLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[],\n" +
                "  \"opcionesIncorrectas\":[]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserConCantidadDePreguntasUnoEnIncorrectaLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[],\n" +
                "  \"opcionesIncorrectas\":[\"Yes\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserConCantidadDePreguntasUnoEnCorrectaLanzaFormatoInvalidoError() {
        ParserMultipleChoiceParcial parser = new ParserMultipleChoiceParcial();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"Nope\"],\n" +
                "  \"opcionesIncorrectas\":[]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }
}
