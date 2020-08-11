package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class JuegoTest {

    // Test unitarios

    // iniciarPartidas
    @Test
    public void iniciarPartidasNoSeEjecutaEntoncesLasPartidasNoExisten() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        Juego juego = new Juego(preguntas, jugadores);

        assertFalse(juego.existePartida());
    }

    @Test
    public void inicarPartidasSeEjecutaEntoncesLasPartidasArrancan() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        assertTrue(juego.existePartida());
    }

    @Test
    public void iniciarPartidasConPartidasYaIniciadasNoHaceNada() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        juego.siguientePartida();
        Partida partida = juego.obtenerPartida();
        assertNotNull(partida);

        juego.iniciarPartidas();

        Partida partidaSigueinte = juego.obtenerPartida();
        assertEquals(partida, partidaSigueinte);
    }

    // existePartida
    @Test
    public void existePartidaSinIniciarPartidasEsFalse() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);

        assertFalse(juego.existePartida());
    }

    @Test
    public void existePartidaConArrayDePreguntasVacioDevuelveFalse() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        assertFalse(juego.existePartida());
    }

    @Test
    public void existePartidaConUnaPreguntaYUnJugadorEsTrue() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        assertTrue(juego.existePartida());
    }

    @Test
    public void existePartidaRecorriendoTodasLasPartidasLaUltimaDevuelveFalse() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        assertTrue(juego.existePartida()); // partida con pregunta1

        juego.siguientePartida();
        assertTrue(juego.existePartida()); // partida con pregunta2

        juego.siguientePartida();
        assertTrue(juego.existePartida()); // partida con pregunta3

        juego.siguientePartida();

        assertFalse(juego.existePartida());
    }

    // sigueintePartida
    @Test
    public void siguientePartidaIncrementaLaPartidaDeAUnoHastaCompletarLaCantiadDePreguntas() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        Partida partida1 = juego.obtenerPartida();
        assertNotNull(partida1);

        juego.siguientePartida();

        Partida partida2 = juego.obtenerPartida();
        assertNotNull(partida2);

        juego.siguientePartida();

        Partida partida3 = juego.obtenerPartida();
        assertNotNull(partida3);

        juego.siguientePartida();

        Partida partida4 = juego.obtenerPartida();
        assertNull(partida4);

        assertNotEquals(partida1, partida2);
        assertNotEquals(partida2, partida3);
        assertNotEquals(partida1, partida3);
    }

    // obtenerPartida
    @Test
    public void obtenerPartidaDevuelveLaPartidaDeLaPreguntaCorrespondiente() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        Pregunta pregunta1 = mock(Pregunta.class);
        Pregunta pregunta2 = mock(Pregunta.class);
        Pregunta pregunta3 = mock(Pregunta.class);

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);
        preguntas.add(pregunta3);

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        Partida partida1 = juego.obtenerPartida();
        partida1.iniciarTurnos();
        assertEquals(pregunta1, partida1.obtenerJugada().obtenerPregunta());

        juego.siguientePartida();
        Partida partida2 = juego.obtenerPartida();
        partida2.iniciarTurnos();
        assertEquals(pregunta2, partida2.obtenerJugada().obtenerPregunta());

        juego.siguientePartida();
        Partida partida3 = juego.obtenerPartida();
        partida3.iniciarTurnos();
        assertEquals(pregunta3, partida3.obtenerJugada().obtenerPregunta());
    }

    @Test
    public void obtenerJugadaAntesDeIniciarPartidasDevuelveNull() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);

        assertNull(juego.obtenerPartida());
    }

    @Test
    public void obtenerPartidaLuegoDeTodasLasPartidasDevuelveNull() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();
        assertNotNull(juego.obtenerPartida());

        juego.siguientePartida();
        assertNotNull(juego.obtenerPartida());

        juego.siguientePartida();
        assertNotNull(juego.obtenerPartida());

        juego.siguientePartida();
        assertNull(juego.obtenerPartida());
    }

    @Test
    public void obtenerPartidasDosVecesSeguidasDevuevleLaMismaPartida() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mock(Jugador.class));

        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));
        preguntas.add(mock(Pregunta.class));

        Juego juego = new Juego(preguntas, jugadores);
        juego.iniciarPartidas();

        Partida partida1 = juego.obtenerPartida();
        assertEquals(partida1, juego.obtenerPartida());

        juego.siguientePartida();

        Partida partida2 = juego.obtenerPartida();
        assertEquals(partida2, juego.obtenerPartida());
    }
}
