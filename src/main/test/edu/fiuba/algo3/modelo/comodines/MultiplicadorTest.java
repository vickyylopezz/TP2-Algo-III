package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultiplicadorTest {
    @Test
    public void seCreaMultiplicadorConJugadorNuloYSeClanzaExcepcion(){
        assertThrows(ComodinError.class, () -> new Multiplicador(0));
    }
    @Test
    public void seCreaMultiplicadorConFactorNuloYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Multiplicador(0));
    }

    @Test
    public void seCreaMultiplicadorConFactorNegativoYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Multiplicador(-1));
    }

    @Test
    public void seCreaMultiplicadorConFactorPositivoYDevuelveElFactor() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertEquals(2,multiplicador.factor());
    }

    @Test
    void recibeUnJugadorYDevuelveElJugador() throws ComodinError {
        Jugador jugador = mock(Jugador.class);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.definirJugador(jugador);

        assertEquals(jugador,multiplicador.obtenerJugador());
    }

    @Test
    void recibeUnJugadorNuloYSeLanzaExcepcion() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () -> multiplicador.definirJugador(null));
    }

    /*@Test
    public void seAplicaEnPreguntaConPenalidadYComodinSeGuardaEnRespuesta() throws ComodinError, RespuestaError, JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalizacion()).thenReturn(true);

        Jugador jugador = new Jugador("Juan");

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        Opcion opcionCorrecta = new Opcion("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.aplicarARespuesta(respuesta);

        assertEquals(1,respuesta.comodines().size());
    }

    @Test
    public void seAplicaEnPreguntaSinPenalidadYSeLanzaExcepcion() throws ComodinError, RespuestaError, JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalizacion()).thenReturn(false);

        Jugador jugador = new Jugador("Juan");

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        Opcion opcionCorrecta = new Opcion("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.aplicarARespuesta(respuesta));
    }*/

    @Test
    public void seValidaPreguntaConPenalidadYComodinSeGuardaEnJugada() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalizacion()).thenReturn(true);

        Jugador jugador = new Jugador("Juan");

        Jugada jugada = new Jugada(pregunta,jugador);

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        Opcion opcionCorrecta = new Opcion("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.validarPregunta(jugada);

        assertEquals(1,jugada.obtenerComodines().size());
    }

    @Test
    public void seValidaPreguntaSinPenalidadYSeLanzaExcepcion() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalizacion()).thenReturn(false);

        Jugador jugador = new Jugador("Juan");

        Jugada jugada = new Jugada(pregunta,jugador);

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        Opcion opcionCorrecta = new Opcion("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.validarPregunta(jugada));
    }
}
