package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RespuestaTest {
    @Test
    public void seCreaRespuestaIndicandoPreguntaYJugador() {
        PreguntaMock preguntaMock = new PreguntaMock();
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(preguntaMock,jugador);

        assertEquals(jugador,respuesta.jugador);
        assertEquals(preguntaMock,respuesta.pregunta);
    }

    @Test
    public void seCreaRespuestaYListaDeOpcionesElgidasEstaVacia() {
        PreguntaMock preguntaMock = new PreguntaMock();
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(preguntaMock,jugador);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertTrue((opcionesElegidas).isEmpty());
    }

    @Test
    public void seAgregaUnaOpcionAListaDeOpcionesElegidasYSuTamanioEsUno() throws RespuestaError {
        PreguntaMock preguntaMock = new PreguntaMock();
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(preguntaMock,jugador);

        Opcion opcion = new Opcion("Si",0);
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(1,(opcionesElegidas).size());
    }

    @Test
    public void seAgregaDosOpcionesIgualesAListaDeOpcionesElegidasYSeLanzaExcepcion() throws RespuestaError {
        PreguntaMock preguntaMock = new PreguntaMock();
        Jugador jugador = new Jugador("Juan");
        Respuesta respuesta = new Respuesta(preguntaMock, jugador);

        Opcion opcion = new Opcion("Si",0);
        respuesta.agregarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> respuesta.agregarOpcion(opcion));
    }
}
