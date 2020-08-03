package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExclusividadTest {
    @Test
    public void seCreaExclusividadConFactorNuloYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Exclusividad(0));
    }

    @Test
    public void seCreaExclusividadConFactorNegativoYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Exclusividad(-1));
    }

    @Test
    public void seCreaExclusividadConFactorPositivoYDevuelveElFactor() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        assertEquals(2,exclusividad.factor());
    }

    @Test
    void recibeUnJugadorYDevuelveElJugador() throws ComodinError {
        Jugador jugador = mock(Jugador.class);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);

        assertEquals(jugador,exclusividad.obtenerJugador());
    }

    @Test
    void recibeUnJugadorNuloYSeLanzaExcepcion() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        assertThrows(ComodinError.class, () -> exclusividad.definirJugador(null));
    }

    /*@Test
    public void seAplicaEnPreguntaSinPenalidadYComodinSeGuardaEnRespuesta() throws ComodinError, RespuestaError, JugadaError {
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
    public void seAplicaEnPreguntaConPenalidadYSeLanzaExcepcion() throws ComodinError, RespuestaError, JugadaError {
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
    public void seValidaPreguntaSinPenalidadYComodinSeGuardaEnJugada() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalizacion()).thenReturn(false);

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
    public void seValidaPreguntaConPenalidadYSeLanzaExcepcion() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalizacion()).thenReturn(true);

        Jugador jugador = new Jugador("Juan");

        Jugada jugada = new Jugada(pregunta,jugador);

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        Opcion opcionCorrecta = new Opcion("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.validarPregunta(jugada));
    }
}
