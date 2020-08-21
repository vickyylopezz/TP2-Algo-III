package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.comodin.AplicacionDeComodinInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorNoTieneAlComodinError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
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

    // tituloPregunta
    @Test
    public void tituloPreguntaDevuelveElTituloDeLaPregunta() {
        String titulo = "Titulo de la pregunta ok";

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerTitulo()).thenReturn(titulo);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(titulo, jugada.tituloPregunta());
    }

    // obtenerPregunta
    @Test
    public void obtenerPreguntaDevuelveLaPreguntaPasadaEnElContructor() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(pregunta, jugada.obtenerPregunta());
    }

    // obtenerJugador
    @Test
    public void obtenerJugadorDevuelveJugadorPasadoEnElContructor() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(jugador, jugada.obtenerJugador());
    }

    // opciones
    @Test
    public void opcionesDevuelveLasOpcionesDeLaPregunta() {
        ArrayList<Opcion> opciones = new ArrayList<>();

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opciones);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(opciones, jugada.opciones());
    }

    // comodines
    @Test
    public void comodinesDevuelveLosComodinesDelJugador() {
        Pregunta pregunta = mock(Pregunta.class);

        ArrayList<Comodin> comodines = new ArrayList<>();
        Jugador jugador = mock(Jugador.class);
        when(jugador.obtenerComodines()).thenReturn(comodines);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertEquals(comodines, jugada.comodines());
    }

    // seleccionarOpcion
    @Test
    public void seleccionarOpcionConOpcionInvalidaNoLanzaExcepcion() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        assertDoesNotThrow(() -> jugada.deseleccionarOpcion(null));
    }

    @Test
    public void seleccionarDosVecesLaMismaOpcionNoLanzaExcepcion() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        jugada.seleccionarOpcion(opcion);

        assertDoesNotThrow(() -> jugada.seleccionarOpcion(opcion));
    }

    @Test
    public void seleccionarOpcionLeAgregaLaOpcion() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        jugada.seleccionarOpcion(opcion);

        ArrayList<Opcion> opciones = jugada.opcionesSeleccionadas();

        assertEquals(1, opciones.size());
        assertTrue(opciones.contains(opcion));
    }

    @Test
    public void seleccionarVariasOpcionesLeAgregaTodasLasOpciones() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        jugada.seleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion2);
        jugada.seleccionarOpcion(opcion3);

        ArrayList<Opcion> opciones = jugada.opcionesSeleccionadas();

        assertEquals(3, opciones.size());

        assertTrue(opciones.contains(opcion1));
        assertTrue(opciones.contains(opcion2));
        assertTrue(opciones.contains(opcion3));
    }

    // deseleccionarOpcion
    @Test
    public void deseleccionarOpcionConOpcionInvalidaNoLanzaExcepcion() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcionInvalida = mock(Opcion.class);
        assertDoesNotThrow(() -> jugada.deseleccionarOpcion(opcionInvalida));
    }

    @Test
    public void deseleccionarDosVecesLaMismaOpcionNoLanzaExcepcion() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        jugada.seleccionarOpcion(opcion);
        jugada.deseleccionarOpcion(opcion);

        assertDoesNotThrow(() -> jugada.deseleccionarOpcion(opcion));
    }

    @Test
    public void deseleccionarOpcionSacaLaOpcionDeLaRespuesta() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);

        jugada.seleccionarOpcion(opcion);
        jugada.deseleccionarOpcion(opcion);

        ArrayList<Opcion> opciones = jugada.opcionesSeleccionadas();

        assertEquals(0, opciones.size());
        assertFalse(opciones.contains(opcion));
    }

    @Test
    public void deseleccionarOpcionSacaLaOpcionPasadaComoParametro() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);

        jugada.seleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion2);
        jugada.deseleccionarOpcion(opcion1);

        ArrayList<Opcion> opciones = jugada.opcionesSeleccionadas();

        assertEquals(1, opciones.size());

        assertFalse(opciones.contains(opcion1));
        assertTrue(opciones.contains(opcion2));
    }

    // seleccionarComodin
    @Test
    public void seleccionarComodinConComodinNoGuardadoEnJugadorLanzaJugadorError() throws JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinInvalido = mock(Comodin.class);
        Jugador jugador = mock(Jugador.class);
        doThrow(JugadorNoTieneAlComodinError.class).when(jugador).validarComodin(comodinInvalido);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadorError.class, () -> jugada.seleccionarComodin(comodinInvalido));
    }

    @Test
    public void seleccionarComodinConComodinInvalidoPreguntaLanzaComodinError() throws JugadorError, ComodinError {
        Comodin comodinInvalido = mock(Comodin.class);

        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinInvalido);

        Jugada jugada = new Jugada(pregunta, jugador);

        doThrow(AplicacionDeComodinInvalidaError.class).when(comodinInvalido).validarPregunta(pregunta);

        assertThrows(ComodinError.class, () -> jugada.seleccionarComodin(comodinInvalido));
    }

    @Test
    public void seleccionarComodinDosVecesElMismoLanzaRemplazaElNuevoPorElQueYaEstaba() throws JugadorError, ComodinError {
        Comodin comodinAnterior = mock(Comodin.class);
        Comodin comodinNuevo = mock(Comodin.class);

        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinAnterior);
        doNothing().when(jugador).validarComodin(comodinNuevo);

        Jugada jugada = new Jugada(pregunta, jugador);
        doNothing().when(comodinAnterior).validarPregunta(pregunta);
        doNothing().when(comodinNuevo).validarPregunta(pregunta);

        jugada.seleccionarComodin(comodinAnterior);
        assertEquals(comodinAnterior, jugada.comodinSeleccionado());

        jugada.seleccionarComodin(comodinNuevo);
        assertEquals(comodinNuevo, jugada.comodinSeleccionado());
    }

    // opcionesSeleccionadas
    @Test
    public void opcionesSeleccionadasDevuelveLasOpcionesSeleccionadas() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        jugada.seleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion2);
        jugada.deseleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion3);

        ArrayList<Opcion> opciones = jugada.opcionesSeleccionadas();

        assertEquals(2, opciones.size());

        assertFalse(opciones.contains(opcion1));
        assertTrue(opciones.contains(opcion2));
        assertTrue(opciones.contains(opcion3));
    }

    // comodinSeleccionado
    @Test
    public void comodinSeleccionadDevuelveElUltimoComodinSelecionado() throws ComodinError, JugadorError {
        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);

        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodin1);
        doNothing().when(jugador).validarComodin(comodin2);

        Pregunta pregunta = mock(Pregunta.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        doNothing().when(comodin1).validarPregunta(pregunta);
        doNothing().when(comodin2).validarPregunta(pregunta);

        jugada.seleccionarComodin(comodin1);
        jugada.seleccionarComodin(comodin2);

        Comodin comodin = jugada.comodinSeleccionado();

        assertEquals(comodin, comodin2);
    }

    // opcionesValidas
    @Test
    public void opcionesValidasDevuelveTodasLasOpcionesAntesSeleccionarAlgunaOpcion() {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opciones = new ArrayList<>();
        opciones.add(opcion1);
        opciones.add(opcion2);
        opciones.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opciones);
        when(pregunta.opcionesSeleccionables(new ArrayList<>())).thenReturn(opciones);

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        ArrayList<Opcion> opcionesValidas = jugada.opcionesValidas();

        for (Opcion opcion: opcionesValidas) {
            assertTrue(opciones.contains(opcion));
        }
        assertEquals(opciones.size(), opcionesValidas.size());
    }

    @Test
    public void opcionesValidasDevuelveTodasLasOpcionesValidasDespuesDeSeleccionarOpcion() {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opcionesValidas = new ArrayList<>();
        opcionesValidas.add(opcion1);
        opcionesValidas.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.seleccionarOpcion(opcion2);

        when(pregunta.opcionesSeleccionables(jugada.opcionesSeleccionadas()))
                .thenReturn(opcionesValidas);

        assertEquals(opcionesValidas, jugada.opcionesValidas());
    }

    @Test
    public void opcionesValidasDevuelveLasOpcionesNoSeleccionadas() {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opcionesValidas = new ArrayList<>();
        opcionesValidas.add(opcion1);

        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.seleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion3);
        jugada.deseleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion2);

        when(pregunta.opcionesSeleccionables(jugada.opcionesSeleccionadas()))
                .thenReturn(opcionesValidas);

        assertEquals(opcionesValidas, jugada.opcionesValidas());
    }

    // comodinesValidos
    @Test
    public void comodinesValidosDevuelveTodosLosComodinesAntesDeSeleccionarComodin() {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin1);
        comodines.add(comodin2);
        comodines.add(comodin3);

        Jugador jugador = mock(Jugador.class);
        when(jugador.obtenerComodines()).thenReturn(comodines);

        Jugada jugada = new Jugada(pregunta, jugador);

        ArrayList<Comodin> comodinesValidos = jugada.comodinesValidos();

        for (Comodin comodin: comodinesValidos) {
            assertTrue(comodines.contains(comodin));
        }
        assertEquals(comodines.size(), comodinesValidos.size());
    }

    @Test
    public void comodinesValidosDevuelveTodosLosComodinesMenosElSeleccionado() throws ComodinError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodin1);
        comodines.add(comodin2);
        comodines.add(comodin3);

        Jugador jugador = mock(Jugador.class);
        when(jugador.obtenerComodines()).thenReturn(comodines);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.seleccionarComodin(comodin3);

        ArrayList<Comodin> comodinesValidos = jugada.comodinesValidos();

        assertEquals(2, comodinesValidos.size());

        assertTrue(comodinesValidos.contains(comodin1));
        assertTrue(comodinesValidos.contains(comodin2));
        assertFalse(comodinesValidos.contains(comodin3));
    }

    // obtenerRespuesta
    @Test
    public void obtenerRespuestaTeDevuelveLaRespuestaDeLaJugada() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Respuesta respuesta = jugada.obtenerRespuesta();

        assertEquals(jugador, respuesta.obtenerJugador());
        assertEquals(pregunta, respuesta.obtenerPregunta());
    }
}
