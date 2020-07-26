package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RespuestaTest {
    @Test
    public void seCreaRespuestaIndicandoPreguntaYJugador() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(pregunta,jugador);

        assertEquals(jugador,respuesta.jugador);
        assertEquals(pregunta,respuesta.pregunta);
    }

    @Test
    public void seCreaRespuestaYListaDeOpcionesElgidasEstaVacia() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(pregunta,jugador);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertTrue((opcionesElegidas).isEmpty());
    }

    @Test
    public void seAgregaUnaOpcionAListaDeOpcionesElegidasYSuTamanioEsUno() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(pregunta,jugador);

        Opcion opcion = new Opcion("Si",0);
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(1,(opcionesElegidas).size());
    }

    @Test
    public void seAgregaDosOpcionesIgualesAListaDeOpcionesElegidasYSeLanzaExcepcion() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si",0);
        respuesta.agregarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> respuesta.agregarOpcion(opcion));
    }
}
