package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class JugadorTest {

    // Test unitarios

    // nombre
    @Test
    public void crearJugadorConNombreLeAsignaElNombre() {
        Jugador carlos = new Jugador("Carlos");

        String nombre = carlos.nombre();

        assertEquals(nombre, "Carlos");
    }

    // cambiarNombre
    @Test
    public void cambiarNombreLeCambiaElNombreAlJugador() {
        Jugador jugador = new Jugador("Carlos");

        assertEquals("Carlos", jugador.nombre());

        jugador.cambiarNombre("Maria");

        assertEquals("Maria", jugador.nombre());
    }

    // obtenerRespuestas
    @Test
    public void jugadorInicialmenteSinRespuestas() {
        Jugador carlos = new Jugador("Carlos");

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertTrue(respuestas.isEmpty());
    }

    @Test
    public void obtenerRespuestaDevuelveUnArregloCopiadoDelQueContineElJugador() {
        Respuesta respuesta1 = mock(Respuesta.class);
        Respuesta respuesta2 = mock(Respuesta.class);
        Respuesta respuesta3 = mock(Respuesta.class);

        Jugador carlos = new Jugador("Carlos");
        carlos.agregarRespuesta(respuesta1);
        carlos.agregarRespuesta(respuesta2);
        carlos.agregarRespuesta(respuesta3);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();
        assertEquals(3, respuestas.size());

        respuestas.remove(respuesta1);
        assertEquals(2, respuestas.size());

        ArrayList<Respuesta> respuestasJugador = carlos.obtenerRespuestas();
        assertEquals(3, respuestasJugador.size());
    }

    @Test
    public void obtenerRespuestaDevuelveLasRespuestasAgregadas() {
        Respuesta respuesta1 = mock(Respuesta.class);
        Respuesta respuesta2 = mock(Respuesta.class);
        Respuesta respuesta3 = mock(Respuesta.class);

        Jugador carlos = new Jugador("Carlos");
        carlos.agregarRespuesta(respuesta1);
        carlos.agregarRespuesta(respuesta2);
        carlos.agregarRespuesta(respuesta3);
        carlos.sacarRespuesta(respuesta2);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();
        assertEquals(2, respuestas.size());
        assertTrue(respuestas.contains(respuesta1));
        assertFalse(respuestas.contains(respuesta2));
        assertTrue(respuestas.contains(respuesta3));
    }

    // agregarRespuesta
    @Test
    public void agregarRespuestaGuardaLaRespuestaEnElJugador() {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta = mock(Respuesta.class);
        carlos.agregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertTrue(respuestas.contains(respuesta));
    }

    @Test
    public void agregarRespuestaYaGuardadaNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();
        assertEquals(1, respuestas.size());
        assertTrue(respuestas.contains(respuesta));

        carlos.agregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestasNuevas = carlos.obtenerRespuestas();
        assertEquals(1, respuestasNuevas.size());
        assertTrue(respuestasNuevas.contains(respuesta));
    }

    @Test
    public void agregarRespuestaNulNoPasaNada() {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();
        assertEquals(1, respuestas.size());
        assertTrue(respuestas.contains(respuesta));

        carlos.agregarRespuesta(null);

        ArrayList<Respuesta> respuestasNuevas = carlos.obtenerRespuestas();
        assertEquals(1, respuestasNuevas.size());
        assertTrue(respuestasNuevas.contains(respuesta));
    }

    @Test
    public void agregarVariasRespuestasSeGuardanTodas() {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta1 = mock(Respuesta.class);
        Respuesta respuesta2 = mock(Respuesta.class);
        Respuesta respuesta3 = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta1);
        carlos.agregarRespuesta(respuesta2);
        carlos.agregarRespuesta(respuesta3);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertEquals(respuestas.size(), 3);
        assertTrue(respuestas.contains(respuesta1));
        assertTrue(respuestas.contains(respuesta2));
        assertTrue(respuestas.contains(respuesta3));
    }

    // sacarRespuesta
    @Test
    public void sacarRespuestaSacaLasRespuesta() {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta);
        carlos.sacarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();
        assertTrue(respuestas.isEmpty());
    }

    @Test
    public void sacarRespuestaNulaNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta = mock(Respuesta.class);
        carlos.agregarRespuesta(respuesta);

        carlos.sacarRespuesta(null);

        ArrayList<Respuesta> respuestasNuevas = carlos.obtenerRespuestas();
        assertEquals(1, respuestasNuevas.size());
        assertTrue(respuestasNuevas.contains(respuesta));
    }

    @Test
    public void sacarRespuestaQueNoEstaEnLasRespuestasNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();
        assertEquals(0, respuestas.size());

        Respuesta respuesta = mock(Respuesta.class);
        carlos.sacarRespuesta(respuesta);

        respuestas = carlos.obtenerRespuestas();
        assertEquals(0, respuestas.size());
    }

    @Test
    public void sacarRespuestaSacaLaRespuestaCorrecta() {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta1 = mock(Respuesta.class);
        Respuesta respuesta2 = mock(Respuesta.class);
        Respuesta respuesta3 = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta1);
        carlos.agregarRespuesta(respuesta2);
        carlos.agregarRespuesta(respuesta3);

        carlos.sacarRespuesta(respuesta2);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertEquals(2, respuestas.size());
        assertTrue(respuestas.contains(respuesta1));
        assertFalse(respuestas.contains(respuesta2));
        assertTrue(respuestas.contains(respuesta3));
    }

    // obtenerComodines
    @Test
    public void jugadorInicialmenteSinComodines() {
        Jugador carlos = new Jugador("Carlos");

        ArrayList<Comodin> comodines = carlos.obtenerComodines();

        assertTrue(comodines.isEmpty());
    }

    @Test
    public void obtenerComodinesDevuelveUnArregloCopiadoDelQueContineElJugador() {
        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        Jugador carlos = new Jugador("Carlos");
        carlos.agregarComodin(comodin1);
        carlos.agregarComodin(comodin2);
        carlos.agregarComodin(comodin3);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertEquals(3, comodines.size());

        comodines.remove(comodin1);
        assertEquals(2, comodines.size());

        ArrayList<Comodin> comodinesJugador = carlos.obtenerComodines();
        assertEquals(3, comodinesJugador.size());
    }

    @Test
    public void obtenerComodinesDevuelveLosComodinesAgregados() {
        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        Jugador carlos = new Jugador("Carlos");
        carlos.agregarComodin(comodin1);
        carlos.agregarComodin(comodin2);
        carlos.agregarComodin(comodin3);
        carlos.sacarComodin(comodin1);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertEquals(2, comodines.size());
        assertFalse(comodines.contains(comodin1));
        assertTrue(comodines.contains(comodin2));
        assertTrue(comodines.contains(comodin3));
    }

    // agregarComodin
    @Test
    public void agregarComodinGuardaElComodin() {
        Jugador carlos = new Jugador("Carlos");

        Comodin comodin = mock(Comodin.class);
        carlos.agregarComodin(comodin);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();

        assertTrue(comodines.contains(comodin));
    }

    @Test
    public void agregarComodinQueYaFueAgregadoNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");
        Comodin comodin = mock(Comodin.class);

        carlos.agregarComodin(comodin);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));

        carlos.agregarComodin(comodin);

        comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));
    }

    @Test
    public void agregarComodinNuloNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");
        Comodin comodin = mock(Comodin.class);

        carlos.agregarComodin(comodin);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));

        carlos.agregarComodin(null);

        comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));
    }

    @Test
    public void agregarVariosComodinesSeGuardanTodos() {
        Jugador carlos = new Jugador("Carlos");
        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        carlos.agregarComodin(comodin1);
        carlos.agregarComodin(comodin2);
        carlos.agregarComodin(comodin3);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();

        assertEquals(comodines.size(), 3);
        assertTrue(comodines.contains(comodin1));
        assertTrue(comodines.contains(comodin2));
        assertTrue(comodines.contains(comodin3));
    }

    // sacarComodin
    @Test
    public void sacarComodinSacaElComodinDeLosComodinesDelJugador() {
        Jugador carlos = new Jugador("Carlos");

        Comodin comodin = mock(Comodin.class);

        carlos.agregarComodin(comodin);
        carlos.sacarComodin(comodin);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertTrue(comodines.isEmpty());
    }

    @Test
    public void sacarComodinQueNoEstaEnLosComodinesDelJugadorNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");

        Comodin comodin = mock(Comodin.class);
        carlos.agregarComodin(comodin);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));

        Comodin comodinNoIngresado = mock(Comodin.class);
        carlos.sacarComodin(comodinNoIngresado);

        comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));
    }

    @Test
    public void sacarComodinNuloNoHaceNada() {
        Jugador carlos = new Jugador("Carlos");

        Comodin comodin = mock(Comodin.class);
        carlos.agregarComodin(comodin);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));

        carlos.sacarComodin(null);

        comodines = carlos.obtenerComodines();
        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));
    }

    @Test
    public void sacarComodinSacaElComodinCorrecto() {
        Jugador carlos = new Jugador("Carlos");

        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        carlos.agregarComodin(comodin1);
        carlos.agregarComodin(comodin2);
        carlos.agregarComodin(comodin3);

        carlos.sacarComodin(comodin2);

        ArrayList<Comodin> comodines = carlos.obtenerComodines();

        assertEquals(2, comodines.size());
        assertTrue(comodines.contains(comodin1));
        assertFalse(comodines.contains(comodin2));
        assertTrue(comodines.contains(comodin3));
    }

    // puntajeTotal
    @Test
    public void calcularPuntajeTotalSinRespuestasEsCero() {
        Jugador carlos = new Jugador("Carlos");

        Punto puntaje = carlos.puntajeTotal();

        assertEquals(puntaje.obtenerValor(), 0);
    }

    // validarComodin
    @Test
    public void validarComodinLanzaExcepcionSiElComodinNoEstaDentroDeLosComodinesAgregados() {
        Jugador carlos = new Jugador("Carlos");

        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);
        Comodin comodin4 = mock(Comodin.class);

        carlos.agregarComodin(comodin1);
        carlos.agregarComodin(comodin2);
        carlos.agregarComodin(comodin3);

        assertThrows(JugadorError.class, ()->carlos.validarComodin(comodin4));
    }
}
