package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
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
}
