package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JugadorTest {
    @Test
    public void crearJugadorConNombreLeAsignaElNombre() {
        Jugador carlos = new Jugador("Carlos");

        String nombre = carlos.Nombre();

        assertEquals(nombre, "Carlos");
    }

    @Test
    public void jugadorInicialmenteSinRespuestas() {
        Jugador carlos = new Jugador("Carlos");

        ArrayList<Respuesta> respuestas = carlos.ObtenerRespuestas();

        assertTrue(respuestas.isEmpty());
    }

    @Test
    public void agregarRespuestaGuardaLaRespuestaEnElJugador() throws JugadorError {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta = new Respuesta(null, null);

        carlos.AgregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.ObtenerRespuestas();

        assertTrue(respuestas.contains(respuesta));
    }

    @Test
    public void agregarDosVecesLaMismaRespuestaLanzaExcepcionRespuestasIgualesError() throws JugadorError {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta = new Respuesta(null, null);

        carlos.AgregarRespuesta(respuesta);

        assertThrows(JugadorError.class, () -> carlos.AgregarRespuesta(respuesta));
    }

    @Test
    public void agregarVariasRespuestasSeGuardanTodas() throws JugadorError {
        Jugador carlos = new Jugador("Carlos");
        Respuesta respuesta1 = new Respuesta(null, null);
        Respuesta respuesta2 = new Respuesta(null, null);
        Respuesta respuesta3 = new Respuesta(null, null);

        carlos.AgregarRespuesta(respuesta1);
        carlos.AgregarRespuesta(respuesta2);
        carlos.AgregarRespuesta(respuesta3);

        ArrayList<Respuesta> respuestas = carlos.ObtenerRespuestas();

        assertEquals(respuestas.size(), 3);
        assertTrue(respuestas.contains(respuesta1));
        assertTrue(respuestas.contains(respuesta2));
        assertTrue(respuestas.contains(respuesta3));
    }

    @Test
    public void calcularPuntajeTotalSinRespuestasEsCero() {
        Jugador carlos = new Jugador("Carlos");

        Integer puntaje = carlos.PuntajeTotal();

        assertEquals(puntaje, 0);
    }

    @Test
    public void calcularPuntajeTotalConUnaRespuestaDevuelveElPuntajeCorrecto() {
        Jugador carlos = new Jugador("Carlos");

        Opcion opcion = new Opcion("Verdadero", 1);
        Respuesta respuesta = new Respuesta(null, null);
        respuesta.agregarOpcion(opcion);

        try {
            carlos.AgregarRespuesta(respuesta);
        } catch (JugadorError jugadorError) {
            jugadorError.printStackTrace();
        }
        VerdaderoFalsoClasico preguntaVF = new VerdaderoFalsoClasico("¿Tu nombre empieza con la letra C?");
        ArrayList<Respuesta> respuestaArrayList = new ArrayList<Respuesta>();
        respuestaArrayList.add(respuesta);
        ArrayList<Integer> puntaje = preguntaVF.obtenerPuntajes(respuestaArrayList);

        assertEquals(1, puntaje.get(0));
    }

    @Test
    public void calcularPuntajeTotalConVariasRespuestaDevuelveElPuntajeCorrecto() {
        Jugador carlos = new Jugador("Carlos");

        Opcion opcion1 = new Opcion("Verdadero", 1);
        Respuesta respuesta1 = new Respuesta(null, null);
        respuesta1.agregarOpcion(opcion1);

        Opcion opcion2 = new Opcion("Verdadero", -3);
        Respuesta respuesta2 = new Respuesta(null, null);
        respuesta2.agregarOpcion(opcion2);

        Opcion opcion3 = new Opcion("Verdadero", 5);
        Respuesta respuesta3 = new Respuesta(null, null);
        respuesta3.agregarOpcion(opcion3);

        try {
            carlos.AgregarRespuesta(respuesta1);
            carlos.AgregarRespuesta(respuesta2);
            carlos.AgregarRespuesta(respuesta3);
        } catch (JugadorError jugadorError) {
            jugadorError.printStackTrace();
        }

        VerdaderoFalsoClasico preguntaVF = new VerdaderoFalsoClasico("¿Tu nombre empieza con la letra C?");
        ArrayList<Respuesta> respuestaArrayList = new ArrayList<Respuesta>();
        for (Respuesta respuesta : carlos.ObtenerRespuestas()){
            respuestaArrayList.add(respuesta);
            ArrayList<Integer> puntaje = preguntaVF.obtenerPuntajes(respuestaArrayList);
            carlos.sumarPuntaje(puntaje.get(0));
            respuestaArrayList.clear();
        }

        Integer puntaje = carlos.PuntajeTotal();

        assertEquals(3, puntaje);
    }
}
