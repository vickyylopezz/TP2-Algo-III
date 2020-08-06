package edu.fiuba.algo3.modelo.juego;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PartidaTest {

    // Test unitarios

    // contructor
    @Test
    public void partidaSeCreaConUnaPreguntaYUnaListaDeJugadores() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        assertDoesNotThrow(() -> { new Partida(pregunta, jugadores); });
    }

    // siguienteTurno
    @Test
    public void siguienteTurnoSinJugadoresDevuelveFalse() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Partida partida = new Partida(pregunta, jugadores);

        assertFalse(partida.siguienteTurno());
    }

    @Test
    public void siguienteTurnoConUnJugadorDevuelveTrueYDespuesNull() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertTrue(partida.siguienteTurno());
        assertFalse(partida.siguienteTurno());
    }

    @Test
    public void siguienteTurnoConVariosJugadoresDevuelveLaMismaCantidadTruePorJugadorYDespuesNull() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertTrue(partida.siguienteTurno());
        assertTrue(partida.siguienteTurno());
        assertTrue(partida.siguienteTurno());
        assertTrue(partida.siguienteTurno());

        assertFalse(partida.siguienteTurno());
    }

    @Test
    public void siguienteTurnoUnaVezFinalizadoLosTurnosSiempreDevuelveFalse() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertTrue(partida.siguienteTurno());
        assertTrue(partida.siguienteTurno());

        assertFalse(partida.siguienteTurno());
        assertFalse(partida.siguienteTurno());
        assertFalse(partida.siguienteTurno());
        assertFalse(partida.siguienteTurno());
    }

    // obtenerJugada
    @Test
    public void obtenerJugadaTeDevuelveLaJugadaDelTurno() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        for (Jugador jugador: jugadores) {
            partida.siguienteTurno();

            Jugada jugada = partida.obtenerJugada();

            assertEquals(jugada.obtenerJugador(), jugador);
            assertEquals(jugada.obtenerPregunta(), pregunta);
        }
    }

    @Test
    public void obtenerJugadaDespuesDeTodosLosTurnosDevuelveNull() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        partida.siguienteTurno();
        assertNotNull(partida.obtenerJugada());
        partida.siguienteTurno();
        assertNotNull(partida.obtenerJugada());

        partida.siguienteTurno();
        assertNull(partida.obtenerJugada());
    }

    @Test
    public void obtenerJugadaDespuesDeTodosLosTurnosSiempreNull() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        partida.siguienteTurno();
        assertNotNull(partida.obtenerJugada());
        partida.siguienteTurno();
        assertNotNull(partida.obtenerJugada());

        partida.siguienteTurno();
        assertNull(partida.obtenerJugada());
        assertNull(partida.obtenerJugada());
        assertNull(partida.obtenerJugada());
        assertNull(partida.obtenerJugada());
    }

    @Test
    public void obtenerJugadaDosVecesSobreElMismoTurnoTeDevuelveLaMismaJugada() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        partida.siguienteTurno();

        Jugada jugada1 = partida.obtenerJugada();
        Jugada jugada2 = partida.obtenerJugada();

        assertEquals(jugada1, jugada2);
    }

    @Test
    public void obtenerJugadaVariasVecesSobreElMismoTurnoTeDevuelveLaMismaJugada() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        partida.siguienteTurno();

        Jugada jugada1 = partida.obtenerJugada();
        Jugada jugada2 = partida.obtenerJugada();
        Jugada jugada3 = partida.obtenerJugada();
        Jugada jugada4 = partida.obtenerJugada();

        assertEquals(jugada1, jugada2);
        assertEquals(jugada2, jugada3);
        assertEquals(jugada3, jugada4);
    }
}
