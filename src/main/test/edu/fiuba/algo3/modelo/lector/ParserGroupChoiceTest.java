package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserGroupChoiceTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonTipoInvalidoLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"lskdnf\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinPreguntaLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinGruposLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonGrupoTipoInvalidoLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"lskdnf\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":true,\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonGrupoCantidadMasDeDosLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"lskdnf\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\", \"Colores\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonGrupoCantidadMenosDeDosLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"lskdnf\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinGrupoUnoLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonGrupoUnoTipoInvalidoLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":5,\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinGrupoDosLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"2\",\"4\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonGrupoDosTipoInvalidoLanzaFormatoInvalidoError() {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"2\",\"4\"],\n" +
                "  \"grupo2\":false\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws PreguntaError, LectorFormatoDePreguntaError {
        ParserGroupChoice parser = new ParserGroupChoice();

        String groupChoiceJson = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(groupChoiceJson).getAsJsonObject();

        Pregunta pregunta = parser.parsear(objetoPregunta);

        assertEquals("Agrupar en numero y letas", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(8, pregunta.obtenerOpciones().size());
    }
}
