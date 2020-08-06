package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.PartidaError;
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

    // obtenerPregunta
    @Test
    public void obtenerPreguntaTeDevuelveLaPreguntaPasadaEnElConstructor() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Partida partida = new Partida(pregunta, jugadores);

        assertEquals(pregunta, partida.obtenerPregunta());
    }

    // obtenerJugadores
    @Test
    public void obtenerJugadoresTeDevuelveLosJugadoresPasadosEnElConstructor() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Partida partida = new Partida(pregunta, jugadores);

        assertEquals(jugadores, partida.obtenerJugadores());
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
    public void obtenerJugadaSinHaberLlamadoSiguienteTurnoDevuelveNull() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertNull(partida.obtenerJugada());
    }

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

    // obtenerRespuestas
    @Test
    public void obtenerRespuestasSinHaberTerminadoLosTurnosLanzaPartidaError() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertThrows(PartidaError.class, partida::obtenerRespuestas);
    }

    @Test
    public void obtenerRespuestasConTurnosFinalizadosNoLanzaExcepcion() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.siguienteTurno(); // primer turno
        partida.siguienteTurno(); // turnos finalizados

        assertDoesNotThrow(partida::obtenerRespuestas);
    }

    @Test
    public void obtenerRespuestasDevuelveLasRespuestasGeneradasPorLasJugadas() throws PartidaError {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.siguienteTurno();
        Respuesta respuesta1 = partida.obtenerJugada().obtenerRespuesta();
        partida.siguienteTurno();
        Respuesta respuesta2 = partida.obtenerJugada().obtenerRespuesta();
        partida.siguienteTurno();

        ArrayList<Respuesta> respuestasPartida = partida.obtenerRespuestas();

        assertEquals(2, respuestasPartida.size());
        assertTrue(respuestasPartida.contains(respuesta1));
        assertTrue(respuestasPartida.contains(respuesta2));
    }

    // @Test
    /*  refactorizaciones necesarias:
    *       cambio de nombre en comodin validarPregunta por validarJugada
    *       comodin tiene que aceptar un arreglo de respuestas aplicarARespuestas
    *           violacion de principio open-close.
    *       Jugada tiene que sacar el comodin del jugadr o marcarlo como utilizado linea 307
    public void obtenerRespuestasDevuelveLasRespuestasConLosComodinesAplicados() throws PartidaError, JugadorError, ComodinError {
        // inicializo el entorno
        Pregunta pregunta = mock(Pregunta.class);
        Comodin comodin = mock(Comodin.class);

        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodin);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);

        Partida partida = new Partida(pregunta, jugadores);
        partida.siguienteTurno();

        Jugada jugada = partida.obtenerJugada();
        doNothing().when(comodin).validarPregunta(jugada);

        // parte a testear
        jugada.seleccionarComodin(comodin);
        partida.siguienteTurno();

        // al finalizar el turno los comodines se marcan como utilizado
        verify(jugador, times(1)).sacarComodin(comodin);

        ArrayList<Respuesta> respuestasPartida = partida.obtenerRespuestas();
        assertEquals(1, respuestasPartida.size());

        ArrayList<Comodin> comodinesAplicados = respuestasPartida.get(0).comodinesAplicados();

        assertEquals(1, comodinesAplicados.size());
        assertTrue(comodinesAplicados.contains(comodin));
    } */
}
