package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.excepciones.comodin.AplicacionDeComodinInvalidaError;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.respuesta.RespuestaError;
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
    public void seleccionarDosVecesLaMismaOpcionLanzaRespuestaError() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        jugada.seleccionarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> jugada.seleccionarOpcion(opcion));
    }

    @Test
    public void seleccionarOpcionLeAgregaLaOpcion() throws RespuestaError {
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
    public void seleccionarVariasOpcionesLeAgregaTodasLasOpciones() throws RespuestaError {
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
    public void deseleccionarOpcionConOpcionInvalidaLanzaRespuestaError() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcionInvalida = mock(Opcion.class);
        assertThrows(RespuestaError.class, () -> jugada.deseleccionarOpcion(opcionInvalida));
    }

    @Test
    public void deseleccionarDosVecesLaMismaOpcionLanzaRespuestaError() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        jugada.seleccionarOpcion(opcion);
        jugada.deseleccionarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> jugada.deseleccionarOpcion(opcion));
    }

    @Test
    public void deseleccionarOpcionSacaLaOpcionDeLaRespuesta() throws RespuestaError {
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
    public void deseleccionarOpcionSacaLaOpcionPasadaComoParametro() throws RespuestaError {
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
        doThrow(JugadorError.class).when(jugador).validarComodin(comodinInvalido);

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

        doThrow(AplicacionDeComodinInvalidaError.class).when(comodinInvalido).validarPregunta(jugada);

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
        doNothing().when(comodinAnterior).validarPregunta(jugada);
        doNothing().when(comodinNuevo).validarPregunta(jugada);

        jugada.seleccionarComodin(comodinAnterior);
        assertEquals(comodinAnterior, jugada.comodinSeleccionado());

        jugada.seleccionarComodin(comodinNuevo);
        assertEquals(comodinNuevo, jugada.comodinSeleccionado());
    }

    // opcionesSeleccionadas
    @Test
    public void opcionesSeleccionadasDevuelveLasOpcionesSeleccionadas() throws RespuestaError {
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

        doNothing().when(comodin1).validarPregunta(jugada);
        doNothing().when(comodin2).validarPregunta(jugada);

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

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        ArrayList<Opcion> opcionesValidas = jugada.opcionesValidas();

        for (Opcion opcion: opcionesValidas) {
            assertTrue(opciones.contains(opcion));
        }
        assertEquals(opciones.size(), opcionesValidas.size());
    }

    @Test
    public void opcionesValidasDevuelveTodasLasOpcionesDespuesDeSeleccionarOpcion() throws RespuestaError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opciones = new ArrayList<>();
        opciones.add(opcion1);
        opciones.add(opcion2);
        opciones.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opciones);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.seleccionarOpcion(opcion2);

        ArrayList<Opcion> opcionesValidas = jugada.opcionesValidas();

        assertTrue(opcionesValidas.contains(opcion1));
        assertFalse(opcionesValidas.contains(opcion2));
        assertTrue(opcionesValidas.contains(opcion3));
    }

    @Test
    public void opcionesValidasDevuelveLasOpcionesNoSeleccionadas() throws RespuestaError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opciones = new ArrayList<>();
        opciones.add(opcion1);
        opciones.add(opcion2);
        opciones.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opciones);

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.seleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion3);
        jugada.deseleccionarOpcion(opcion1);
        jugada.seleccionarOpcion(opcion2);

        ArrayList<Opcion> opcionesValidas = jugada.opcionesValidas();

        assertEquals(1, opcionesValidas.size());
        assertEquals(opcion1, opcionesValidas.get(0));
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

    /*
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

    // iniciar
    @Test
    public void iniciarDosVecesLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciar();

        assertThrows(JugadaError.class, jugada::iniciar);
    }

    // finalizar
    @Test
    public void finalizarSinHaberIniciadoLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, jugada::finalizar);
    }

    @Test
    public void finalizarTiempoDosVecesLanzaJugadaError() throws JugadaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciar();
        jugada.finalizar();

        assertThrows(JugadaError.class, jugada::finalizar);
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

    // seleccionarOpcion
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
    public void seleccionarOpcionSinIniciarLanzaJugadaError() throws JugadaError, PreguntaError {
        Opcion opcionValida = mock(Opcion.class);
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.seleccionarOpcion(opcionValida));
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

    // seleccionarComodin
    @Test
    public void seleccionarComodinSinIniciarTiempoLanzaJugadaError() throws JugadaError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.seleccionarComodin(comodinValido));
    }

    @Test
    public void seleccionarComodinFinalizadoElTiempoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, () -> jugada.seleccionarComodin(comodinValido));
    }

    @Test
    public void seleccionarComodinAgregaALosComodinesDevueltos() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarComodin(comodinValido);
        jugada.finalizarTiempo();

        ArrayList<Comodin> comodines = jugada.comodinesSeleccionados();

        assertEquals(1, comodines.size());
        assertEquals(comodinValido, comodines.get(0));
    }

    // deseleccionarComodin
    @Test
    public void deseleccionarComodinSinIniciarTiempoLanzaJugadaError() throws JugadaError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);

        assertThrows(JugadaError.class, () -> jugada.deseleccionarComodin(comodinValido));
    }

    @Test
    public void deseleccionarComodinNoAplicadoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();

        assertThrows(JugadaError.class, () -> jugada.deseleccionarComodin(comodinValido));
    }

    @Test
    public void deseleccionarComodinDosVecesElMismoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarComodin(comodinValido);

        jugada.deseleccionarComodin(comodinValido);

        assertThrows(JugadaError.class, () -> jugada.deseleccionarComodin(comodinValido));
    }

    @Test
    public void deseleccionarComodinFinalizadoElTiempoLanzaJugadaError() throws JugadaError, JugadorError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Comodin comodinValido = mock(Comodin.class);
        doNothing().when(comodinValido).validarPregunta(pregunta);
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).validarComodin(comodinValido);

        Jugada jugada = new Jugada(pregunta, jugador);
        jugada.iniciarTiempo();
        jugada.seleccionarComodin(comodinValido);
        jugada.finalizarTiempo();

        assertThrows(JugadaError.class, () -> jugada.deseleccionarComodin(comodinValido));
    }

    @Test
    public void deseleccionarComodinesSacaComodinDeLosComodinesAplicados() throws JugadaError, JugadorError, ComodinError {
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
        jugada.seleccionarComodin(comodin1);
        jugada.seleccionarComodin(comodin2);
        jugada.deseleccionarComodin(comodin1);
        jugada.finalizarTiempo();

        ArrayList<Comodin> comodines = jugada.comodinesSeleccionados();

        assertEquals(1, comodines.size());
        assertEquals(comodin2, comodines.get(0));
    }
    */
}
