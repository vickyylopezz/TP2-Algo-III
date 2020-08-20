package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserMultipleChoiceClasicoTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

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
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

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
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinOpcionesCorrectasLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesCorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":true,\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinOpcionesIncorrectasLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonConOpcionesIncorrectasQueNoSonArrayLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la True/false\"],\n" +
                "  \"opcionesIncorrectas\":false\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws PreguntaError, LectorFormatoDePreguntaError {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
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
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[],\n" +
                "  \"opcionesIncorrectas\":[]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserConCantidadDePreguntasUnoEnIncorrectaLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[],\n" +
                "  \"opcionesIncorrectas\":[\"Yes\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserConCantidadDePreguntasUnoEnCorrectaLanzaFormatoInvalidoError() {
        ParserMultipleChoiceClasico parser = new ParserMultipleChoiceClasico();

        String multipleChoiceJson = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"Nope\"],\n" +
                "  \"opcionesIncorrectas\":[]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(multipleChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }
}
