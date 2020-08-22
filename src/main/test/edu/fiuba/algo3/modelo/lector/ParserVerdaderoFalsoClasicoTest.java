package edu.fiuba.algo3.modelo.lector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.lector.parsers.ParserVerdaderoFalso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserVerdaderoFalsoClasicoTest {
    @Test
    public void parsearConJsonSinTipoLanzaFormatoInvalidoError() {
        ParserVerdaderoFalso parser = new ParserVerdaderoFalso();

        String verdaderoFalso = "{" +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"," +
                "  \"respuesta\":true" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonTipoInvalidoLanzaFormatoInvalidoError() {
        ParserVerdaderoFalso parser = new ParserVerdaderoFalso();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFClasicoooo\"," +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"," +
                "  \"respuesta\":true" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinPreguntaLanzaFormatoInvalidoError() {
        ParserVerdaderoFalso parser = new ParserVerdaderoFalso();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFClasico\"," +
                "  \"respuesta\":\"False\"" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parsearConJsonSinRespuesLanzaFormatoInvalidoError() {
        ParserVerdaderoFalso parser = new ParserVerdaderoFalso();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFClasico\"," +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        assertThrows(LectorFormatoDePreguntaError.class, () -> parser.parsear(objetoPregunta));
    }

    @Test
    public void parserDevuelveLaPreguntaCorrecta() throws LectorFormatoDePreguntaError {
        ParserVerdaderoFalso parser = new ParserVerdaderoFalso();

        String verdaderoFalso = "{" +
                "  \"tipo\":\"VFClasico\"," +
                "  \"pregunta\":\"¿Esto es un verdadero Falso?\"," +
                "  \"respuesta\":true" +
                "}";
        JsonObject objetoPregunta = JsonParser.parseString(verdaderoFalso).getAsJsonObject();

        Pregunta pregunta = parser.parsear(objetoPregunta);

        assertEquals("¿Esto es un verdadero Falso?", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
    }
}
