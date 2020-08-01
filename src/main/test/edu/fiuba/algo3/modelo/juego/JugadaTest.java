package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.util.tiempo.MiliSegundo;
import edu.fiuba.algo3.modelo.util.tiempo.Segundo;
import edu.fiuba.algo3.modelo.util.tiempo.Tiempo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JugadaTest {

    // Test unitarios

    // contructor
    @Test
    public void seCreaConUnaPreguntaYUnJugador() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        assertDoesNotThrow(() -> new Jugada(pregunta, jugador));
    }

    @Test
    public void seCreaConUnaPreguntaNulaLanzaExcepcionJugadaError() {
        Jugador jugador = mock(Jugador.class);
        assertThrows(JugadaError.class, () -> new Jugada(null, jugador));
    }

    @Test
    public void seCreaConUnJugadorNuloLanzaExcepcionJugadaError() {
        Pregunta pregunta = mock(Pregunta.class);
        assertThrows(JugadaError.class, () -> new Jugada(pregunta, null));
    }

    @Test
    public void tituloPreguntaDevuelveElTituloDeLaPregunta() throws JugadaError {
        String titulo = "Titulo de la pregunta ok";

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTitulo()).thenReturn(titulo);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(titulo, jugada.tituloPregunta());
    }

    @Test
    public void tiempoPreguntaDevuelveElTiempoDisponibleParaSeleccionarOpciones() throws JugadaError {
        Segundo seg = Segundo.crearConLiteral(5L);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTiempo()).thenReturn(seg);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(seg.valor(), jugada.tiempoPregunta().segundos().valor());
    }

    @Test
    public void tiempoTrancurridoSinIniciarTiempoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, jugada::tiempoTrancurrido);
    }

    @Test
    public void tiempoTranscurridoConTiempoIniciadoNoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        assertDoesNotThrow(jugada::tiempoTrancurrido);
    }

    //@Test
    public void tiempoTrancurridoEsElTiempoCorrecto() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        try { Thread.sleep(1000L); }
        catch (InterruptedException e) { fail(); }

        MiliSegundo ms = jugada.tiempoTrancurrido().miliSegundos();

        int maxErrorTiempo = 50;
        long variacionTiempo = Math.abs(ms.valor() - 1000L);
        assertTrue(variacionTiempo < maxErrorTiempo);
    }

    @Test
    public void tiempoRestanteSinIniciarTiempoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, jugada::tiempoRestante);
    }

    @Test
    public void tiempoRestanteConTiempoIniciadoNoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTiempo()).thenReturn(Segundo.crearConLiteral(5L));

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        assertDoesNotThrow(jugada::tiempoRestante);
    }

    //@Test
    public void tiempoRestanteEsElTiempoCorrecto() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTiempo()).thenReturn(Segundo.crearConLiteral(5L));

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        try { Thread.sleep(1000L); }
        catch (InterruptedException e) { fail(); }

        MiliSegundo ms = jugada.tiempoRestante().miliSegundos();

        // tiempos en milisegundos
        int maxErrorTiempo = 50;
        long variacionTiempo = Math.abs(ms.valor() - 4000L);
        assertTrue(variacionTiempo < maxErrorTiempo);
    }
}
