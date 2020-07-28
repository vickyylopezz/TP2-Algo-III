package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class JugadorTest {
    @Test
    public void crearJugadorConNombreLeAsignaElNombre() {
        Jugador carlos = new Jugador("Carlos");

        String nombre = carlos.nombre();

        assertEquals(nombre, "Carlos");
    }

    @Test
    public void jugadorInicialmenteSinRespuestas() {
        Jugador carlos = new Jugador("Carlos");

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertTrue(respuestas.isEmpty());
    }

    @Test
    public void agregarRespuestaGuardaLaRespuestaEnElJugador() throws JugadorError, RespuestaError {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta = new Respuesta(null, null);

        carlos.agregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertTrue(respuestas.contains(respuesta));
    }

    @Test
    public void agregarDosVecesLaMismaRespuestaLanzaExcepcionRespuestasIgualesError() throws JugadorError, RespuestaError {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta = new Respuesta(null, null);

        carlos.agregarRespuesta(respuesta);

        assertThrows(JugadorError.class, () -> carlos.agregarRespuesta(respuesta));
    }

    @Test
    public void agregarVariasRespuestasSeGuardanTodas() throws JugadorError, RespuestaError {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta1 = new Respuesta(null, null);
        Respuesta respuesta2 = new Respuesta(null, null);
        Respuesta respuesta3 = new Respuesta(null, null);

        carlos.agregarRespuesta(respuesta1);
        carlos.agregarRespuesta(respuesta2);
        carlos.agregarRespuesta(respuesta3);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertEquals(respuestas.size(), 3);
        assertTrue(respuestas.contains(respuesta1));
        assertTrue(respuestas.contains(respuesta2));
        assertTrue(respuestas.contains(respuesta3));
    }

    @Test
    public void calcularPuntajeTotalSinRespuestasEsCero() {
        Jugador carlos = new Jugador("Carlos");

        Integer puntaje = carlos.puntajeTotal();

        assertEquals(puntaje, 0);
    }

    @Test
    public void calcularPuntajeTotalConUnaRespuestaDevuelveElPuntajeCorrecto() throws RespuestaError, PreguntaError {
        Jugador carlos = new Jugador("Carlos");
        VerdaderoFalsoClasico preguntaVF = new VerdaderoFalsoClasico("¿Tu nombre empieza con la letra C?", 15);

        Opcion opcion = new Opcion("Verdadero", 1);
        Respuesta respuesta = new Respuesta(preguntaVF, null);
        respuesta.agregarOpcion(opcion);

        try {
            carlos.agregarRespuesta(respuesta);
        } catch (JugadorError jugadorError) {
            jugadorError.printStackTrace();
        }
        
        Integer puntaje = respuesta.obtenerPuntaje();

        assertEquals(1, puntaje);
    }

    @Test
    public void calcularPuntajeTotalConVariasRespuestaDevuelveElPuntajeCorrecto() throws RespuestaError, PreguntaError {
        Jugador carlos = new Jugador("Carlos");
        VerdaderoFalsoClasico preguntaVF = new VerdaderoFalsoClasico("¿Tu nombre empieza con la letra C?", 15);

        Opcion opcion1 = new Opcion("Verdadero", 1);
        Respuesta respuesta1 = new Respuesta(preguntaVF, null);
        respuesta1.agregarOpcion(opcion1);

        Opcion opcion2 = new Opcion("Verdadero", -3);
        Respuesta respuesta2 = new Respuesta(preguntaVF, null);
        respuesta2.agregarOpcion(opcion2);

        Opcion opcion3 = new Opcion("Verdadero", 5);
        Respuesta respuesta3 = new Respuesta(preguntaVF, null);
        respuesta3.agregarOpcion(opcion3);

        try {
            carlos.agregarRespuesta(respuesta1);
            carlos.agregarRespuesta(respuesta2);
            carlos.agregarRespuesta(respuesta3);
        } catch (JugadorError jugadorError) {
            jugadorError.printStackTrace();
        }

        for (Respuesta respuesta : carlos.obtenerRespuestas()){
            Integer puntajePorRespuesta = respuesta.obtenerPuntaje();
            carlos.sumarPuntaje(puntajePorRespuesta);
        }

        Integer puntaje = carlos.puntajeTotal();

        assertEquals(3, puntaje);
    }
}
