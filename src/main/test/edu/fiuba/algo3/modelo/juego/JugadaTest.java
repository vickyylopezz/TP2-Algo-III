package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.util.tiempo.MiliSegundo;
import edu.fiuba.algo3.modelo.util.tiempo.Segundo;
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
    public void seleccionarOpcionFinalizadoElTiempoLanzaJugadaError() throws JugadaError, PreguntaError {
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

    // deseleccionarOpcion
    @Test
    public void deseleccionarOpcionSinIniciarTiempoLanzaJugadaError() throws JugadaError, PreguntaError {
        Opcion opcionValida = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcionValida);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.deseleccionarOpcion(opcionValida));
    }

    @Test
    public void deseleccionarOpcionConOpcionInvalidaLanzaPreguntaError() throws JugadaError, PreguntaError {
        Opcion opcionInvalida = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doThrow(PreguntaError.class).when(pregunta).validarOpcion(opcionInvalida);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(PreguntaError.class, () -> jugada.deseleccionarOpcion(opcionInvalida));
    }

    @Test
    public void deseleccionarDosVecesLaMismaOpcionLanzaRespuestaError() throws JugadaError, PreguntaError, RespuestaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarOpcion(opcion);

        jugada.deseleccionarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> jugada.deseleccionarOpcion(opcion));
    }

    @Test
    public void deseleccionarOpcionFinalizadoElTiempoLanzaJugadaError() throws JugadaError, PreguntaError, RespuestaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarOpcion(opcion);
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, () -> jugada.deseleccionarOpcion(opcion));
    }

    @Test
    public void deseleccionarOpcionSacaLaOpcionDeLaRespuesta() throws JugadaError, PreguntaError, RespuestaError {
        Opcion opcion = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarOpcion(opcion);
        jugada.deseleccionarOpcion(opcion);
        jugada.finalizarTiempo();

        Respuesta respuesta = jugada.obtenerRespuesta();
        ArrayList<Opcion> opciones = respuesta.obtenerOpcionesElegidas();

        assertEquals(0, opciones.size());
        assertFalse(opciones.contains(opcion));
    }

    @Test
    public void deseleccionarOpcionSacaLaOpcionPasadaComoParametro() throws JugadaError, PreguntaError, RespuestaError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);
        doNothing().when(pregunta).validarOpcion(opcion1);
        doNothing().when(pregunta).validarOpcion(opcion2);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion2);
        jugada.deseleccionarOpcion(opcion1);
        jugada.finalizarTiempo();

        Respuesta respuesta = jugada.obtenerRespuesta();
        ArrayList<Opcion> opciones = respuesta.obtenerOpcionesElegidas();

        assertEquals(1, opciones.size());
        assertFalse(opciones.contains(opcion1));
        assertTrue(opciones.contains(opcion2));
    }

    // aplicarComodin
    @Test
    public void aplicarComodinSinIniciarTiempoLanzaJugadaError() throws JugadaError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.aplicarComodin(comodinValido));
    }

    @Test
    public void aplicarComodinConComodinNoGuardadoEnJugadorLanzaJugadorError() throws JugadaError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinInvalido = mock(Comodin.class);
        Jugador jugador = mock(Jugador.class);
        doThrow(JugadorError.class).when(jugador).validarComodin(comodinInvalido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(JugadorError.class, () -> jugada.aplicarComodin(comodinInvalido));
    }

    @Test
    public void aplicarComodinConComodinInvalidoPreguntaLanzaComodinError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinInvalido = mock(Comodin.class);
        doThrow(ComodinError.class).when(comodinInvalido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinInvalido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(ComodinError.class, () -> jugada.aplicarComodin(comodinInvalido));
    }

    @Test
    public void aplicarComodinDosVecesElMismoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.aplicarComodin(comodinValido);

        assertThrows(JugadaError.class, () -> jugada.aplicarComodin(comodinValido));
    }

    @Test
    public void aplicarComodinFinalizadoElTiempoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, () -> jugada.aplicarComodin(comodinValido));
    }

    @Test
    public void aplicarComodinAgregaALosComodinesDevueltos() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.aplicarComodin(comodinValido);
        jugada.finalizarTiempo();

        ArrayList<Comodin> comodines = jugada.obtenerComodinesAplicados();

        assertEquals(1, comodines.size());
        assertEquals(comodinValido, comodines.get(0));
    }

    // desaplicarComodin
    @Test
    public void desaplicarComodinSinIniciarTiempoLanzaJugadaError() throws JugadaError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.desaplicarComodin(comodinValido));
    }

    @Test
    public void desaplicarComodinNoAplicadoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(JugadaError.class, () -> jugada.desaplicarComodin(comodinValido));
    }

    @Test
    public void desaplicarComodinDosVecesElMismoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.aplicarComodin(comodinValido);

        jugada.desaplicarComodin(comodinValido);

        assertThrows(JugadaError.class, () -> jugada.desaplicarComodin(comodinValido));
    }

    @Test
    public void desaplicarComodinFinalizadoElTiempoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.aplicarComodin(comodinValido);
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, () -> jugada.desaplicarComodin(comodinValido));
    }

    @Test
    public void desaplicarComodinesSacaComodinDeLosComodinesAplicados() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodin1 = mock(Comodin.class);
        doNothing().when(comodin1).validarPregunta(pregunta);

        Comodin comodin2 = mock(Comodin.class);
        doNothing().when(comodin2).validarPregunta(pregunta);

        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodin1);
        doNothing().when(jugador).validarComodin(comodin2);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.aplicarComodin(comodin1);
        jugada.aplicarComodin(comodin2);
        jugada.desaplicarComodin(comodin1);
        jugada.finalizarTiempo();

        ArrayList<Comodin> comodines = jugada.obtenerComodinesAplicados();

        assertEquals(1, comodines.size());
        assertEquals(comodin2, comodines.get(0));
    }

    // obtenerRespuesta
    @Test
    public void obtenerRespuestaSinFinalizarTiempoLanzaJugadaError() throws PreguntaError, JugadaError, RespuestaError {
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

    // obtenerComodinesAplicados
    @Test
    public void obtenerComodinesAplicadosSinFinalizarTiempoLanzaJugadaError() throws JugadaError, ComodinError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.aplicarComodin(comodinValido);

        assertThrows(JugadaError.class, jugada::obtenerComodinesAplicados);
    }

    @Test
    public void obtenerComodinesAplicadosTeDevuelveLosComodinesQueSeAplicaron() throws JugadaError, ComodinError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodin1 = mock(Comodin.class);
        doNothing().when(comodin1).validarPregunta(pregunta);
        Comodin comodin2 = mock(Comodin.class);
        doNothing().when(comodin2).validarPregunta(pregunta);
        Comodin comodin3 = mock(Comodin.class);
        doNothing().when(comodin3).validarPregunta(pregunta);

        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodin1);
        doNothing().when(jugador).validarComodin(comodin2);
        doNothing().when(jugador).validarComodin(comodin3);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        jugada.aplicarComodin(comodin1);
        jugada.aplicarComodin(comodin3);

        jugada.finalizarTiempo();

        ArrayList<Comodin> comodinesAplicados = jugada.obtenerComodinesAplicados();

        assertEquals(2, comodinesAplicados.size());

        assertTrue(comodinesAplicados.contains(comodin1));
        assertFalse(comodinesAplicados.contains(comodin2));
        assertTrue(comodinesAplicados.contains(comodin3));
    }
}
