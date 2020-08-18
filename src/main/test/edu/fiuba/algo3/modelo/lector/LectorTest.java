package edu.fiuba.algo3.modelo.lector;

import edu.fiuba.algo3.modelo.excepciones.lector.LectorError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorSintaxisError;
import edu.fiuba.algo3.modelo.excepciones.lector.LectorFormatoDePreguntaError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    // extraerPreguntas
    @Test
    public void extraerPreguntasTextoNuloDevuelveNull() throws LectorError, PreguntaError {
        Lector lector = new Lector();

        assertNull(lector.extraerPreguntas(null));
    }

    @Test
    public void extraerPreguntasTextoConFormatoJsonInvalidoLanzaLectorErrorSintaxisError() {
        Lector lector = new Lector();

        String cadenaJsonInvalida = "[{/{}}}}}]";

        assertThrows(LectorSintaxisError.class, () -> lector.extraerPreguntas(cadenaJsonInvalida));
    }

    @Test
    public void extraerPreguntasTextoConTipoDePreguntaIndefinidoLanzaLectorTipoPreguntaError() {
        Lector lector = new Lector();

        String cadenaTipoIndefinido = "[" +
                    "{" +
                        "\"tipo\":\"VFClasico\"," +
                        "\"pregunta\":\"¿Es True/False?\"," +
                        "\"respuesta\":\"False\"" +
                    "}," +
                    "{" +
                        "\"pregunta\":\"¿Es True o False?\"," +
                        "\"respuesta\":\"False\"" +
                    "}" +
                "]";

        assertThrows(LectorFormatoDePreguntaError.class, () -> lector.extraerPreguntas(cadenaTipoIndefinido));
    }

    @Test
    public void extraerPreguntasTextoConTipoDePreguntaIncorrectoLanzaLectorTipoPreguntaError() {
        Lector lector = new Lector();

        String cadenaTipoIncorrecto = "[" +
                "{" +
                "\"tipo\":\"VFClasico\"," +
                "\"pregunta\":\"¿Es True/False?\"," +
                "\"respuesta\":\"False\"" +
                "}," +
                "{" +
                "\"tipo\":\"tipologico\"," +
                "\"pregunta\":\"¿Es True o False?\"," +
                "\"respuesta\":\"False\"" +
                "}" +
                "]";

        assertThrows(LectorFormatoDePreguntaError.class, () -> lector.extraerPreguntas(cadenaTipoIncorrecto));
    }

    @Test
    public void extraerPreguntasMedianteUnaCadenaDeJsonConArregloDePreguntasDevuelveArregloDePreguntasEnElMismoOrden() throws PreguntaError, LectorError {
        Lector lector = new Lector();

        String cadenaJson = "[" +
                "{" +
                "\"tipo\":\"VFClasico\"," +
                "\"pregunta\":\"¿Es True/False?\"," +
                "\"respuesta\":\"False\"" +
                "}," +
                "{" +
                "\"tipo\":\"VFPenalidad\"," +
                "\"pregunta\":\"¿Es True o False?\"," +
                "\"respuesta\":\"False\"" +
                "}" +
                "]";

        ArrayList<Pregunta> preguntas = lector.extraerPreguntas(cadenaJson);

        assertEquals(2, preguntas.size());

        assertEquals("¿Es True/False?", preguntas.get(0).obtenerTitulo());
        assertEquals("¿Es True o False?", preguntas.get(1).obtenerTitulo());
    }

    @Test
    public void extraerPreguntasMedianteUnaCadenaDeJsonConArregloDePreguntasDevuelveArregloDePreguntasCorrectas() throws PreguntaError, LectorError {
        Lector lector = new Lector();

        String cadenaJson = "[" +
                "{" +
                "\"tipo\":\"VFClasico\"," +
                "\"pregunta\":\"¿Es True/False?\"," +
                "\"respuesta\":\"False\"" +
                "}," +
                "{" +
                "\"tipo\":\"VFPenalidad\"," +
                "\"pregunta\":\"¿Es True o False?\"," +
                "\"respuesta\":\"False\"" +
                "}" +
                "]";

        ArrayList<Pregunta> preguntas = lector.extraerPreguntas(cadenaJson);

        assertEquals(2, preguntas.size());

        Pregunta pregunta1 = preguntas.get(0);
        Pregunta pregunta2 = preguntas.get(1);

        assertEquals("¿Es True/False?", pregunta1.obtenerTitulo());
        assertEquals("¿Es True o False?", pregunta2.obtenerTitulo());

        assertFalse(pregunta1.conPenalidad());
        assertTrue(pregunta2.conPenalidad());
    }
}
