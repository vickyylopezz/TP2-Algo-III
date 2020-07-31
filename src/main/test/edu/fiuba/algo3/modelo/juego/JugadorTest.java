package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.Composite.*;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
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

        Respuesta respuesta = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta);

        ArrayList<Respuesta> respuestas = carlos.obtenerRespuestas();

        assertTrue(respuestas.contains(respuesta));
    }

    @Test
    public void agregarDosVecesLaMismaRespuestaLanzaExcepcionRespuestasIgualesError() throws JugadorError, RespuestaError {
        Jugador carlos = new Jugador("Carlos");

        Respuesta respuesta = mock(Respuesta.class);

        carlos.agregarRespuesta(respuesta);

        assertThrows(JugadorError.class, () -> carlos.agregarRespuesta(respuesta));
    }

    @Test
    public void agregarVariasRespuestasSeGuardanTodas() throws JugadorError, RespuestaError {
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

    @Test
    public void calcularPuntajeTotalSinRespuestasEsCero() {
        Jugador carlos = new Jugador("Carlos");

        Punto puntaje = carlos.puntajeTotal();

        assertEquals(puntaje.getValor(), 0);
    }

    @Test
    public void calcularPuntajeTotalConUnaRespuestaDevuelveElPuntajeCorrecto() throws RespuestaError, PreguntaError {
        Jugador carlos = new Jugador("Carlos");
        VerdaderoFalsoClasico preguntaVF = new VerdaderoFalsoClasico("¿Tu nombre empieza con la letra C?", 15);

        Opcion opcion = new Opcion("Verdadero", new PuntoPositivo());
        Respuesta respuesta = mock(Respuesta.class);
        respuesta.agregarOpcion(opcion);

        try {
            carlos.agregarRespuesta(respuesta);
        } catch (JugadorError jugadorError) {
            jugadorError.printStackTrace();
        }

        Punto puntaje = respuesta.obtenerPuntaje();

        assertEquals(1, puntaje.getValor());
    }

    @Test
    public void calcularPuntajeTotalConVariasRespuestaDevuelveElPuntajeCorrecto() throws RespuestaError, PreguntaError {
        Jugador carlos = new Jugador("Carlos");
        VerdaderoFalsoClasico preguntaVF = new VerdaderoFalsoClasico("¿Tu nombre empieza con la letra C?", 15);

        Opcion opcion1 = new Opcion("Verdadero", new PuntoPositivo());
        Respuesta respuesta1 = mock(Respuesta.class);
        respuesta1.agregarOpcion(opcion1);

        Opcion opcion2 = new Opcion("Verdadero", new PuntoNegativo());
        Respuesta respuesta2 = mock(Respuesta.class);
        respuesta2.agregarOpcion(opcion2);

        Opcion opcion3 = new Opcion("Verdadero", new PuntoPositivo());
        Respuesta respuesta3 = mock(Respuesta.class);
        respuesta3.agregarOpcion(opcion3);

        try {
            carlos.agregarRespuesta(respuesta1);
            carlos.agregarRespuesta(respuesta2);
            carlos.agregarRespuesta(respuesta3);
        } catch (JugadorError jugadorError) {
            jugadorError.printStackTrace();
        }

        for (Respuesta respuesta : carlos.obtenerRespuestas()){
            carlos.sumarPuntaje(respuesta.obtenerPuntaje());
        }

        Punto puntaje = carlos.puntajeTotal();

        assertEquals(3, puntaje.getValor());
    }
}
