package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.util.tiempo.MiliSegundo;
import edu.fiuba.algo3.modelo.util.tiempo.Segundo;
import edu.fiuba.algo3.modelo.util.tiempo.Tiempo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JugadaTest {

    // Test unitarios

    // contructor
    @Test
    public void seCreaConUnaPreguntaYUnJugador() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        assertDoesNotThrow(() -> new Jugada(pregunta, jugador));
    }

    @Test
    public void seCreaConUnaPreguntaNulaLanzaExcepcionJugadaError() {
        Jugador jugador = mock(Jugador.class);
        assertThrows(JugadaError.class, () -> new Jugada(null, jugador));
    }

    @Test
    public void seCreaConUnJugadorNuloLanzaExcepcionJugadaError() {
        Pregunta pregunta = mock(Pregunta.class);
        assertThrows(JugadaError.class, () -> new Jugada(pregunta, null));
    }

    // tituloPregunta
    @Test
    public void tituloPreguntaDevuelveElTituloDeLaPregunta() throws JugadaError {
        String titulo = "Titulo de la pregunta ok";

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTitulo()).thenReturn(titulo);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(titulo, jugada.tituloPregunta());
    }

    // tiempoPregunta
    @Test
    public void tiempoPreguntaDevuelveElTiempoDisponibleParaSeleccionarOpciones() throws JugadaError {
        Segundo seg = Segundo.crearConLiteral(5L);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTiempo()).thenReturn(seg);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(seg.valor(), jugada.tiempoPregunta().segundos().valor());
    }

    // iniciarTiempo
    @Test
    public void iniciarTiempoDosVecesLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(JugadaError.class, jugada::iniciarTiempo);
    }

    // finalizarTiempo
    @Test
    public void finalizarSinHaberIniciadoTiempoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, jugada::finalizarTiempo);
    }

    @Test
    public void finalizarTiempoDosVecesLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, jugada::finalizarTiempo);
    }

    // tiempoTranscurrido
    @Test
    public void tiempoTrancurridoSinIniciarTiempoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, jugada::tiempoTrancurrido);
    }

    @Test
    public void tiempoTranscurridoConTiempoIniciadoNoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        assertDoesNotThrow(jugada::tiempoTrancurrido);
    }

    //@Test
    public void tiempoTrancurridoEsElTiempoCorrecto() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        try { Thread.sleep(1000L); }
        catch (InterruptedException e) { fail(); }

        MiliSegundo ms = jugada.tiempoTrancurrido().miliSegundos();

        int maxErrorTiempo = 50;
        long variacionTiempo = Math.abs(ms.valor() - 1000L);
        assertTrue(variacionTiempo < maxErrorTiempo);
    }

    // tiempoRestante
    @Test
    public void tiempoRestanteSinIniciarTiempoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, jugada::tiempoRestante);
    }

    @Test
    public void tiempoRestanteConTiempoIniciadoNoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTiempo()).thenReturn(Segundo.crearConLiteral(5L));

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        assertDoesNotThrow(jugada::tiempoRestante);
    }

    //@Test
    public void tiempoRestanteEsElTiempoCorrecto() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTiempo()).thenReturn(Segundo.crearConLiteral(5L));

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.iniciarTiempo();

        try { Thread.sleep(1000L); }
        catch (InterruptedException e) { fail(); }

        MiliSegundo ms = jugada.tiempoRestante().miliSegundos();

        // tiempos en milisegundos
        int maxErrorTiempo = 50;
        long variacionTiempo = Math.abs(ms.valor() - 4000L);
        assertTrue(variacionTiempo < maxErrorTiempo);
    }

    // opciones
    @Test
    public void opcionesDevuelveLasOpcionesDeLaPregunta() throws JugadaError {
        ArrayList<Opcion> opciones = new ArrayList<>();
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opciones);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(opciones, jugada.opciones());
    }

    // comodines
    @Test
    public void comodinesDevuelveLosComodinesDelJugador() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Comodin> comodines = new ArrayList<>();
        Jugador jugador = mock(Jugador.class);
        when(jugador.obtenerComodines()).thenReturn(comodines);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(comodines, jugada.comodines());
    }

    // seleccionarOpcion
    @Test
    public void seleccionarOpcionSinIniciarTiempoLanzaJugadaError() throws JugadaError, PreguntaError {
        Opcion opcionValida = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcionValida);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.seleccionarOpcion(opcionValida));
    }

    @Test
    public void seleccionarOpcionConOpcionInvalidaLanzaPreguntaError() throws JugadaError, PreguntaError {
        Opcion opcionInvalida = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doThrow(PreguntaError.class).when(pregunta).validarOpcion(opcionInvalida);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(PreguntaError.class, () -> jugada.seleccionarOpcion(opcionInvalida));
    }

    @Test
    public void seleccionarDosVecesLaMismaOpcionLanzaRespuestaError() throws JugadaError, PreguntaError, RespuestaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        jugada.seleccionarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> jugada.seleccionarOpcion(opcion));
    }

    @Test
    public void seleccionarOpcionFinalizadoElTiempoLanzaPreguntaError() throws JugadaError, PreguntaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, () -> jugada.seleccionarOpcion(opcion));
    }

    @Test
    public void seleccionarOpcionLeAgregaLaOpcionALaRespuesta() throws JugadaError, PreguntaError, RespuestaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarOpcion(opcion);
        jugada.finalizarTiempo();

        Respuesta respuesta = jugada.obtenerRespuesta();
        ArrayList<Opcion> opciones = respuesta.obtenerOpcionesElegidas();

        assertEquals(1, opciones.size());
        assertTrue(opciones.contains(opcion));
    }

    // obtenerRespuestas
    @Test
    public void obtenerRespuestaSinFinalizarTiempoLanzaPreguntaError() throws PreguntaError, JugadaError, RespuestaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarOpcion(opcion);

        assertThrows(JugadaError.class, jugada::obtenerRespuesta);
    }

    @Test
    public void obtenerRespuestaTeDevuelveLaRespuestaDeLaJugada() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.finalizarTiempo();

        Respuesta respuesta = jugada.obtenerRespuesta();

        assertEquals(jugador, respuesta.obtenerJugador());
        assertEquals(pregunta, respuesta.obtenerPregunta());
    }
}
