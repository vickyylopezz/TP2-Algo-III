package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RespuestaTest {
    @Test
    public void seCreaRespuestaIndicandoPreguntaYJugador() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        assertEquals(jugador, respuesta.jugador);
        assertEquals(pregunta, respuesta.pregunta);
    }

    @Test
    public void seCreaRespuestaYListaDeOpcionesElgidasEstaVacia() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertTrue((opcionesElegidas).isEmpty());
    }

    @Test
    public void seCreaYListaDeComodinesEstaVacia() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Respuesta respuesta = new Respuesta(pregunta,jugador);

        assertTrue(respuesta.obtenerComodines().isEmpty());
    }

    @Test
    public void seAgregaUnaOpcionCorrectaAListaDeOpcionesElegidasYSuTamanioEsUno() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si", new PuntoPositivo());
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion, (opcionesElegidas).get(0));
    }

    @Test
    public void seAgregaUnaOpcionIncorrectaSinPenalidadAListaDeOpcionesElegidasYSuTamanioEsUno() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si", new PuntoPositivo());
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion, (opcionesElegidas).get(0));
    }

    @Test
    public void seAgregaUnaOpcionIncorrectaConPenalidadAListaDeOpcionesElegidasYLaDevuelve() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si", new PuntoNegativo());
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion, (opcionesElegidas).get(0));
    }

    @Test
    public void seAgreganVariasOpcionesCorrectasAListaDeOpcionesElegidasYLasDevuelveEnOrdenQueFueronAgregadas() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = new Opcion("Si", new PuntoPositivo());
        Opcion opcion2 = new Opcion("Si", new PuntoPositivo());
        Opcion opcion3 = new Opcion("Si", new PuntoPositivo());

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion1, (opcionesElegidas).get(0));
        assertEquals(opcion2, (opcionesElegidas).get(1));
        assertEquals(opcion3, (opcionesElegidas).get(2));
    }

    @Test
    public void seAgreganVariasOpcionesIncorrectasSinPenalidadAListaDeOpcionesElegidasYLasDevuelveEnOrdenQueFueronAgregadas() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = new Opcion("Si", new PuntoNulo());
        Opcion opcion2 = new Opcion("Si", new PuntoNulo());
        Opcion opcion3 = new Opcion("Si", new PuntoNulo());

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion1, (opcionesElegidas).get(0));
        assertEquals(opcion2, (opcionesElegidas).get(1));
        assertEquals(opcion3, (opcionesElegidas).get(2));
    }

    @Test
    public void seAgreganVariasOpcionesIncorrectasConPenalidadAListaDeOpcionesElegidasYLasDevuelveEnOrdenQueFueronAgregadas() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = new Opcion("Si", new PuntoNegativo());
        Opcion opcion2 = new Opcion("Si", new PuntoNegativo());
        Opcion opcion3 = new Opcion("Si", new PuntoNegativo());

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion1, (opcionesElegidas).get(0));
        assertEquals(opcion2, (opcionesElegidas).get(1));
        assertEquals(opcion3, (opcionesElegidas).get(2));
    }

    @Test
    public void seAgreganVariasOpcionesAListaDeOpcionesElegidasYLasDevuelveEnOrdenQueFueronAgregadas() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = new Opcion("Si", new PuntoPositivo());
        Opcion opcion2 = new Opcion("Si", new PuntoNulo());
        Opcion opcion3 = new Opcion("Si", new PuntoNegativo());

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        ArrayList<Opcion> opcionesElegidas = respuesta.obtenerOpcionesElegidas();

        assertEquals(opcion1, (opcionesElegidas).get(0));
        assertEquals(opcion2, (opcionesElegidas).get(1));
        assertEquals(opcion3, (opcionesElegidas).get(2));
    }

    @Test
    public void seAgreganDosOpcionesCorrectasIgualesAListaDeOpcionesElegidasYSeLanzaExcepcion() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si", new PuntoPositivo());
        respuesta.agregarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> respuesta.agregarOpcion(opcion));
    }

    @Test
    public void seAgreganDosOpcionesIncorrectasSinPenalidadIgualesAListaDeOpcionesElegidasYSeLanzaExcepcion() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si", new PuntoNulo());
        respuesta.agregarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> respuesta.agregarOpcion(opcion));
    }

    @Test
    public void seAgreganDosOpcionesIncorrectasConPenalidadIgualesAListaDeOpcionesElegidasYSeLanzaExcepcion() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = new Opcion("Si", new PuntoNegativo());
        respuesta.agregarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> respuesta.agregarOpcion(opcion));
    }
}
