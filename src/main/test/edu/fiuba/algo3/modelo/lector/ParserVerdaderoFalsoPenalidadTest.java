package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserVerdaderoFalsoPenalidadTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserVerdaderoFalsoConPenalidad parser = new ParserVerdaderoFalsoConPenalidad();

        String verdaderoFalso = "{" +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"," +
                "  \"respuesta\":true" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonTipoInvalidoLanzaFormatoInvalidoError() {
        ParserVerdaderoFalsoConPenalidad parser = new ParserVerdaderoFalsoConPenalidad();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFPenali\"," +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"," +
                "  \"respuesta\":true" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinPreguntaLanzaFormatoInvalidoError() {
        ParserVerdaderoFalsoConPenalidad parser = new ParserVerdaderoFalsoConPenalidad();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFPenalidad\"," +
                "  \"respuesta\":\"False\"" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinRespuesLanzaFormatoInvalidoError() {
        ParserVerdaderoFalsoConPenalidad parser = new ParserVerdaderoFalsoConPenalidad();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFPenalidad\"," +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws LectorFormatoDePreguntaError {
        ParserVerdaderoFalsoConPenalidad parser = new ParserVerdaderoFalsoConPenalidad();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFPenalidad\"," +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"," +
                "  \"respuesta\":true" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        Pregunta pregunta = parser.parsear(objetoPregunta);

        assertEquals("¿Esto es un verdadero Falso?", pregunta.obtenerTitulo());
        assertTrue(pregunta.conPenalidad());
    }
}
