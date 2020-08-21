package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PartidaTest {

    // Test unitarios

    // iniciarTurnos
    @Test
    public void inicarTurnosNoSeEjecutaEntoncesLosTurnoNoArrancan() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertFalse(partida.existeTurno());
    }

    @Test
    public void inicarTurnosSeEjecutaEntoncesLosTurnoArrancan() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        assertTrue(partida.existeTurno());
    }

    @Test
    public void iniciarTurnosConTurnosYaIniciadoNoHaceNada() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        partida.siguienteTurno();
        Jugada jugada = partida.obtenerJugada();
        assertNotNull(jugada);

        partida.iniciarTurnos();

        Jugada jugadaSiguiente = partida.obtenerJugada();

        assertEquals(jugada, jugadaSiguiente);
    }

    // existeTurno
    @Test
    public void existeTurnoSinIniciarTurnosEsFalse() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);

        assertFalse(partida.existeTurno());
    }

    @Test
    public void existeTurnoConArrayDeJugadoresVacioDevuelveFalse() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        assertFalse(partida.existeTurno());
    }

    @Test
    public void existeTurnoConUnJugadorDevuelveTrue() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        assertTrue(partida.existeTurno());
    }

    @Test
    public void existeTurnoConUnJugadorAlFinalDeLosTurnosDevuelveFalse() {
        Pregunta pregunta = mock(Pregunta.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        partida.siguienteTurno();

        assertFalse(partida.existeTurno());
    }

    @Test
    public void existeTurnoEntrePorCadaJugadaEsTrueAlFinalEsFalse() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        assertTrue(partida.existeTurno());

        partida.siguienteTurno();

        assertTrue(partida.existeTurno());

        partida.siguienteTurno();

        assertTrue(partida.existeTurno());

        partida.siguienteTurno();

        assertFalse(partida.existeTurno());
    }

    // sigueinteTurno
    @Test
    public void siguienteTurnoIncrementaElTurnoDeAUno() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        partida.siguienteTurno();
        assertTrue(partida.existeTurno());

        partida.siguienteTurno();
        assertTrue(partida.existeTurno());

        partida.siguienteTurno();
        assertFalse(partida.existeTurno());
    }

    // obtenerJugada
    @Test
    public void obtenerJugadaDevuelveLaJugadaDelPrimerJugador() {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);
        Jugador jugador3 = mock(Jugador.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        Jugada jugada = partida.obtenerJugada();

        assertEquals(jugada.obtenerJugador(), jugador1);
        assertEquals(jugada.obtenerPregunta(), pregunta);
    }

    @Test
    public void obtenerJugadaTeDevuelveLaJugadaDelTurno() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        for (Jugador jugador: jugadores) {
            Jugada jugada = partida.obtenerJugada();

            assertEquals(jugada.obtenerJugador(), jugador);
            assertEquals(jugada.obtenerPregunta(), pregunta);

            partida.siguienteTurno();
        }
    }

    @Test
    public void obtenerJugadaDespuesDeTodosLosTurnosDevuelveNull() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        assertNotNull(partida.obtenerJugada());
        partida.siguienteTurno();
        assertNotNull(partida.obtenerJugada());

        partida.siguienteTurno();

        assertNull(partida.obtenerJugada());
        assertNull(partida.obtenerJugada());
        assertNull(partida.obtenerJugada());
    }

    @Test
    public void obtenerJugadaVariasVecesSobreElMismoTurnoTeDevuelveLaMismaJugada() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        partida.siguienteTurno();

        Jugada jugada1 = partida.obtenerJugada();
        Jugada jugada2 = partida.obtenerJugada();
        Jugada jugada3 = partida.obtenerJugada();
        Jugada jugada4 = partida.obtenerJugada();

        assertEquals(jugada1, jugada2);
        assertEquals(jugada2, jugada3);
        assertEquals(jugada3, jugada4);
    }

    // finalizarTurnos
    @Test
    public void finalizarTurnosConTurnosExistentesNoHaceNada() throws PuntoError {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));
        jugadores.add(mock(Jugador.class));

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();
        partida.siguienteTurno();

        assertTrue(partida.existeTurno());

        partida.finalizarTurnos();

        assertTrue(partida.existeTurno());
    }

    @Test
    public void finalizarTurnosAgregaLasRespuestasALosJugadores() throws PuntoError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);
        Jugador jugador3 = mock(Jugador.class);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        partida.siguienteTurno();
        partida.siguienteTurno();
        partida.siguienteTurno();

        partida.finalizarTurnos();

        int invocaciones = 1;

        verify(jugador1, times(invocaciones)).agregarRespuesta(any(Respuesta.class));
        verify(jugador2, times(invocaciones)).agregarRespuesta(any(Respuesta.class));
        verify(jugador3, times(invocaciones)).agregarRespuesta(any(Respuesta.class));
    }

    @Test
    public void finalizarTurnosAplicaLosComodinesALasRespuestas() throws JugadorError, ComodinError, PuntoError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodin1 = mock(Comodin.class);
        doNothing().when(comodin1).validarPregunta(pregunta);

        Comodin comodin2 = mock(Comodin.class);
        doNothing().when(comodin2).validarPregunta(pregunta);

        Jugador jugador1 = mock(Jugador.class);
        doNothing().when(jugador1).validarComodin(comodin1);

        Jugador jugador2 = mock(Jugador.class);

        Jugador jugador3 = mock(Jugador.class);
        doNothing().when(jugador3).validarComodin(comodin2);

        when(comodin1.obtenerJugador()).thenReturn(jugador1);
        when(comodin2.obtenerJugador()).thenReturn(jugador3);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        ArrayList<Respuesta> respuestasGeneradas = new ArrayList<>();

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();
        respuestasGeneradas.add(partida.obtenerJugada().obtenerRespuesta());

        partida.obtenerJugada().seleccionarComodin(comodin1);
        partida.siguienteTurno();
        respuestasGeneradas.add(partida.obtenerJugada().obtenerRespuesta());

        // no aplica ningun comodin
        partida.siguienteTurno();
        respuestasGeneradas.add(partida.obtenerJugada().obtenerRespuesta());

        partida.obtenerJugada().seleccionarComodin(comodin2);
        partida.siguienteTurno();

        partida.finalizarTurnos();

        int invocaciones = 1;

        verify(comodin1, times(invocaciones)).aplicarARespuestas(respuestasGeneradas);
        verify(comodin2, times(invocaciones)).aplicarARespuestas(respuestasGeneradas);
    }

    @Test
    public void finalizarTurnosSacaElComodinAplicadoDelJugador() throws JugadorError, ComodinError, PuntoError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodin1 = mock(Comodin.class);
        doNothing().when(comodin1).validarPregunta(pregunta);

        Comodin comodin2 = mock(Comodin.class);
        doNothing().when(comodin2).validarPregunta(pregunta);

        Jugador jugador1 = mock(Jugador.class);
        doNothing().when(jugador1).validarComodin(comodin1);

        Jugador jugador2 = mock(Jugador.class);

        Jugador jugador3 = mock(Jugador.class);
        doNothing().when(jugador3).validarComodin(comodin2);

        when(comodin1.obtenerJugador()).thenReturn(jugador1);
        when(comodin2.obtenerJugador()).thenReturn(jugador3);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Partida partida = new Partida(pregunta, jugadores);
        partida.iniciarTurnos();

        partida.obtenerJugada().seleccionarComodin(comodin1);
        partida.siguienteTurno();

        // no aplica ningun comodin
        partida.siguienteTurno();

        partida.obtenerJugada().seleccionarComodin(comodin2);
        partida.siguienteTurno();

        partida.finalizarTurnos();

        verify(jugador1, times(1)).sacarComodin(comodin1);
        verify(jugador2, times(0)).sacarComodin(any(Comodin.class));
        verify(jugador3, times(1)).sacarComodin(comodin2);
    }

}
