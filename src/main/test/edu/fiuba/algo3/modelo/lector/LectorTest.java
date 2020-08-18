package edu.fiuba.algo3.modelo.lector;

import edu.fiuba.algo3.modelo.excepciones.lector.LectorError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorSintaxisError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LectorTest {
    // extraerPregunta
    @Test
    public void extraerPreguntaTextoNuloDevuelveNull() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        assertNull(lector.extraerPregunta(null));
    }

    @Test
    public void extraerPreguntaTextoConFormatoJsonInvalidoLanzaLectorErrorSintaxisError() {
        Lector lector = new Lector();

        String cadenaJsonInvalida = "[{/{}}}}}]";

        assertThrows(LectorSintaxisError.class, () -> lector.extraerPregunta(cadenaJsonInvalida));
    }

    @Test
    public void extraerPreguntaTextoConTipoDePreguntaIndefinidoLanzaLectorTipoPreguntaError() {
        Lector lector = new Lector();

        String cadenaTipoIndefinido = "{\"pregunta\":\"¿Es True o False?\",\"respuesta\":\"False\"}";

        assertThrows(LectorFormatoDePreguntaError.class, () -> lector.extraerPregunta(cadenaTipoIndefinido));
    }

    @Test
    public void extraerPreguntaTextoConTipoDePreguntaIncorrectoLanzaLectorTipoPreguntaError() {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\"tipo\":\"algo3\",\"pregunta\":\"¿Es True o False?\",\"respuesta\":\"False\"}";

        assertThrows(LectorFormatoDePreguntaError.class, () -> lector.extraerPregunta(cadenaTipoIncorrecto));
    }

    @Test
    public void extraerPreguntaTextoVerdaderoFalsoClasicoDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{" +
                "  \"tipo\":\"VFClasico\"," +
                "  \"pregunta\":\"¿Es True/False?\"," +
                "  \"respuesta\":\"False\"" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("¿Es True/False?", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(2, pregunta.obtenerOpciones().size());
    }

    @Test
    public void extraerPreguntaTextoVerdaderoFalsoConPenalidadDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\n" +
                "  \"tipo\":\"VFPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad?\",\n" +
                "  \"respuesta\":\"True\"\n" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("¿Es True/False con penalidad?", pregunta.obtenerTitulo());
        assertTrue(pregunta.conPenalidad());
        assertEquals(2, pregunta.obtenerOpciones().size());
    }

    @Test
    public void extraerPreguntaTextoMultipleChoiceClasicoDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\n" +
                "  \"tipo\":\"MCClasico\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad MCClasico?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la correcta\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("¿Es True/False con penalidad MCClasico?", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(4, pregunta.obtenerOpciones().size());
    }

    @Test
    public void extraerPreguntaTextoMultipleChoiceParcialDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\n" +
                "  \"tipo\":\"MCParcial\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad MCParcial?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la correcta\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("¿Es True/False con penalidad MCParcial?", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(4, pregunta.obtenerOpciones().size());
    }

    @Test
    public void extraerPreguntaTextoMultipleChoiceConPenalizacionDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\n" +
                "  \"tipo\":\"MCPenalidad\",\n" +
                "  \"pregunta\":\"¿Es True/False con penalidad MCPenalidad?\",\n" +
                "  \"opcionesCorrectas\":[\"nope\", \"si no es la correcta\"],\n" +
                "  \"opcionesIncorrectas\":[\"obiusli\",\"si rick\"]\n" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("¿Es True/False con penalidad MCPenalidad?", pregunta.obtenerTitulo());
        assertTrue(pregunta.conPenalidad());
        assertEquals(4, pregunta.obtenerOpciones().size());
    }

    @Test
    public void extraerPreguntaTextoOrderedChoiceDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\n" +
                "  \"tipo\":\"Ordered\",\n" +
                "  \"pregunta\":\"Ordenar por alfabeto\",\n" +
                "  \"orden\":[\"A\", \"B\", \"C\", \"D\", \"E\"]\n" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("Ordenar por alfabeto", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(5, pregunta.obtenerOpciones().size());
    }

    @Test
    public void extraerPreguntaTextoGroupChoiceDevuelveLaPreguntaCorrecta() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "{\n" +
                "  \"tipo\":\"Group\",\n" +
                "  \"pregunta\":\"Agrupar en numero y letas\",\n" +
                "  \"grupos\":[\"Numeros\", \"Letras\"],\n" +
                "  \"grupo1\":[\"1\", \"3\"],\n" +
                "  \"grupo2\":[\"a\",\"k\"]\n" +
                "}";

        Pregunta pregunta = lector.extraerPregunta(cadenaTipoIncorrecto);

        assertEquals("Agrupar en numero y letas", pregunta.obtenerTitulo());
        assertFalse(pregunta.conPenalidad());
        assertEquals(8, pregunta.obtenerOpciones().size());
    }
}
