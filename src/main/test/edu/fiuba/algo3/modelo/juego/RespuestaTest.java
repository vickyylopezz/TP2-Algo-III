package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RespuestaTest {
    // contructor
    @Test
    public void seCreaRespuestaIndicandoPreguntaYJugador() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        assertEquals(jugador, respuesta.obtenerJugador());
        assertEquals(pregunta, respuesta.obtenerPregunta());
    }

    // opcionesElegidas
    @Test
    public void seCreaRespuestaYListaDeOpcionesElgidasEstaVacia() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        ArrayList<Opcion> opcionesElegidas = respuesta.opcionesElegidas();

        assertTrue(opcionesElegidas.isEmpty());
    }

    @Test
    public void elArregloDeOpcionesNoModificaElArregloDeOpcionesElegidas() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.opcionesElegidas();
        assertEquals(1, opcionesElegidas.size());

        opcionesElegidas.remove(0);

        ArrayList<Opcion> opcionesVerificacion = respuesta.opcionesElegidas();
        assertEquals(1, opcionesVerificacion.size());
    }

    // comodinesAplicados
    @Test
    public void seCreaYListaDeComodinesEstaVacia() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta,jugador);

        ArrayList<Comodin> comodines = respuesta.comodinesAplicados();

        assertTrue(comodines.isEmpty());
    }

    @Test
    public void elArregloDeComodinesNoModificaElArregloDeComodinesElegidos() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Comodin comodin = mock(Comodin.class);
        respuesta.aplicarComodin(comodin);

        ArrayList<Comodin> comodinesAplicados = respuesta.comodinesAplicados();
        assertEquals(1, comodinesAplicados.size());

        comodinesAplicados.remove(0);

        ArrayList<Comodin> comodinesVerificacion = respuesta.comodinesAplicados();
        assertEquals(1, comodinesVerificacion.size());
    }

    // agregarOpcion
    @Test
    public void seAgregaUnaOpcionAListaDeOpcionesElegidasYOpcionesElegidasLaDevuelveEnUnArreglo() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opcionesElegidas = respuesta.opcionesElegidas();

        assertEquals(1, opcionesElegidas.size());

        assertEquals(opcion, opcionesElegidas.get(0));
    }

    @Test
    public void seAgreganVariasOpcionesYOpcionesElegidasLasDevuelveEnOrdenQueFueronAgregadas() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        ArrayList<Opcion> opcionesElegidas = respuesta.opcionesElegidas();

        assertEquals(3, opcionesElegidas.size());

        assertEquals(opcion1, opcionesElegidas.get(0));
        assertEquals(opcion2, opcionesElegidas.get(1));
        assertEquals(opcion3, opcionesElegidas.get(2));
    }

    @Test
    public void seAgreganDosOpcionesIgualesSeLanzaExcepcionRespuestaError() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);
        respuesta.agregarOpcion(opcion);

        assertThrows(RespuestaError.class, () -> respuesta.agregarOpcion(opcion));
    }

    // aplicarComodin
    @Test
    public void seAplicaUnComodinAListaDeComodinesAplicadosYComodinesAplicadosLaDevuelveEnUnArreglo() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Comodin comodin = mock(Comodin.class);
        respuesta.aplicarComodin(comodin);

        ArrayList<Comodin> comodinesAgregados = respuesta.comodinesAplicados();

        assertEquals(1, comodinesAgregados.size());

        assertEquals(comodin, comodinesAgregados.get(0));
    }

    @Test
    public void seAplicanVariosComodinesYComodinesAplicadosLosDevuelveEnOrdenQueFueronAplicados() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Comodin comodin1 = mock(Comodin.class);
        Comodin comodin2 = mock(Comodin.class);
        Comodin comodin3 = mock(Comodin.class);

        respuesta.aplicarComodin(comodin1);
        respuesta.aplicarComodin(comodin2);
        respuesta.aplicarComodin(comodin3);

        ArrayList<Comodin> comodinesAplicados = respuesta.comodinesAplicados();

        assertEquals(3, comodinesAplicados.size());

        assertEquals(comodin1, comodinesAplicados.get(0));
        assertEquals(comodin2, comodinesAplicados.get(1));
        assertEquals(comodin3, comodinesAplicados.get(2));
    }

    @Test
    public void seAplicanDosComodinesIgualesSeLanzaExcepcionRespuestaError() throws RespuestaError {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Comodin comodin = mock(Comodin.class);
        respuesta.aplicarComodin(comodin);

        assertThrows(RespuestaError.class, () -> respuesta.aplicarComodin(comodin));
    }

    // esCorrecta
    @Test
    public void esCorrectasDevuelveTrueSiLasOpcionesSeleccionadasSonCorrectasEnLaPregunta() throws RespuestaError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.opcionesCorrectas(opcionesSeleccionadas)).thenReturn(true);

        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        assertTrue(respuesta.esCorrecta());
    }

    @Test
    public void esCorrectasDevuelveFalseSiLasOpcionesSeleccionadasSonIncorrectasEnLaPregunta() throws RespuestaError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.opcionesCorrectas(opcionesSeleccionadas)).thenReturn(false);

        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        assertFalse(respuesta.esCorrecta());
    }
}
