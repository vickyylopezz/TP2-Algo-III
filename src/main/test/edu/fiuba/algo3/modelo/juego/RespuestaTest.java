package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.comodines.Comodin;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
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
    public void elArregloDeOpcionesNoModificaElArregloDeOpcionesElegidas() {
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
    public void elArregloDeComodinesNoModificaElArregloDeComodinesElegidos() {
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
    public void seAgregaUnaOpcionAListaDeOpcionesElegidasYOpcionesElegidasLaDevuelveEnUnArreglo() {
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
    public void seAgreganVariasOpcionesYOpcionesElegidasLasDevuelveEnOrdenQueFueronAgregadas() {
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
    public void seAgreganDosOpcionesIgualesSoloSeGuardaUnaVez() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);

        respuesta.agregarOpcion(opcion);
        respuesta.agregarOpcion(opcion);

        ArrayList<Opcion> opciones = respuesta.opcionesElegidas();

        assertEquals(1, opciones.size());
        assertTrue(opciones.contains(opcion));
    }

    @Test
    public void seAgregaUnaOpcionNulaNoSeAgregaNada() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion = mock(Opcion.class);

        respuesta.agregarOpcion(opcion);
        respuesta.agregarOpcion(null);

        ArrayList<Opcion> opciones = respuesta.opcionesElegidas();

        assertEquals(1, opciones.size());
        assertTrue(opciones.contains(opcion));
    }

    // sacarOpcion
    @Test
    public void sacarOpcionConOpcionNoIngresadaNoHaceNada() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);

        respuesta.agregarOpcion(opcion1);
        respuesta.sacarOpcion(opcion2);

        ArrayList<Opcion> opciones = respuesta.opcionesElegidas();

        assertEquals(1, opciones.size());
        assertTrue(opciones.contains(opcion1));
    }

    @Test
    public void sacarOpcionConOpcionNulaNoHaceNada() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);

        respuesta.agregarOpcion(opcion1);
        respuesta.sacarOpcion(null);

        ArrayList<Opcion> opciones = respuesta.opcionesElegidas();

        assertEquals(1, opciones.size());
        assertTrue(opciones.contains(opcion1));
    }

    @Test
    public void sacarOpcionSacaLaOpcionCorrectaAgregadaDeLaRespuesta() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);

        ArrayList<Opcion> opcionesElegidas = respuesta.opcionesElegidas();
        assertEquals(2, opcionesElegidas.size());

        respuesta.sacarOpcion(opcion2);

        opcionesElegidas = respuesta.opcionesElegidas();

        assertEquals(1, opcionesElegidas.size());
        assertEquals(opcion1, opcionesElegidas.get(0));
    }

    // aplicarComodin
    @Test
    public void seAplicaUnComodinAListaDeComodinesAplicadosYComodinesAplicadosLaDevuelveEnUnArreglo() {
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
    public void seAplicanVariosComodinesYComodinesAplicadosLosDevuelveEnOrdenQueFueronAplicados() {
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
    public void seAplicanDosComodinesIgualesSoloSeAgregaUno() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Comodin comodin = mock(Comodin.class);

        respuesta.aplicarComodin(comodin);
        respuesta.aplicarComodin(comodin);

        ArrayList<Comodin> comodines = respuesta.comodinesAplicados();

        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));
    }

    @Test
    public void seAplicanUnComodinNuloNoSeAgrega() {
        Pregunta pregunta = mock(Pregunta.class);
        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Comodin comodin = mock(Comodin.class);

        respuesta.aplicarComodin(comodin);
        respuesta.aplicarComodin(null);

        ArrayList<Comodin> comodines = respuesta.comodinesAplicados();

        assertEquals(1, comodines.size());
        assertTrue(comodines.contains(comodin));
    }

    // esCorrecta
    @Test
    public void esCorrectasDevuelveTrueSiLasOpcionesSeleccionadasSonCorrectasEnLaPregunta() throws PuntoError {
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
    public void esCorrectasDevuelveFalseSiLasOpcionesSeleccionadasSonIncorrectasEnLaPregunta() throws PuntoError {
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

    // puntaje
    @Test
    public void puntajeSinOpcionesDevuelvePuntajeNulo() throws PuntoError {
        Punto puntoEsperado = new PuntoNulo();

        ArrayList<Opcion> opcionesVacias = new ArrayList<>();

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.puntajeConOpciones(opcionesVacias)).thenReturn(puntoEsperado);

        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        Punto puntaje = respuesta.puntaje();
        assertEquals(puntoEsperado, puntaje);
    }

    @Test
    public void puntajeConOpcionesSinComodinesDevuelveLoCorrecto() throws PuntoError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.puntajeConOpciones(opcionesSeleccionadas)).thenReturn(new PuntoPositivo());

        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        Punto puntaje = respuesta.puntaje();

        Punto puntoEsperado = new PuntoPositivo();
        assertEquals(puntoEsperado.obtenerValor(), puntaje.obtenerValor());
    }

    @Test
    public void puntajeConOpcionesYUnComodinDevuelveLoCorrecto() throws PuntoError {
        Punto puntajeRespuestaSinComodines = new PuntoPositivo();

        Puntaje puntajeRespuestaConComodin = new Puntaje();
        puntajeRespuestaConComodin.agregarPunto(new PuntoPositivo());
        puntajeRespuestaConComodin.agregarPunto(new PuntoPositivo());

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        Comodin comodin = mock(Comodin.class); // comodin que multiplica por 2 el puntaje
        when(comodin.aplicarComodinAPunto(puntajeRespuestaSinComodines)).thenReturn(puntajeRespuestaConComodin);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.puntajeConOpciones(opcionesSeleccionadas)).thenReturn(puntajeRespuestaSinComodines);

        Jugador jugador = mock(Jugador.class);

        Respuesta respuesta = new Respuesta(pregunta, jugador);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        respuesta.aplicarComodin(comodin);

        Punto puntaje = respuesta.puntaje();

        assertEquals(puntajeRespuestaConComodin, puntaje);
    }

    @Test
    public void puntajeConOpcionesYVariosComodinesDevuelveLoCorrecto() throws PuntoError {
        Punto puntajeSinComodines = new PuntoPositivo();

        Puntaje puntajeConComodin1 = new Puntaje();
        for (int i = 0; i < 2; i++) puntajeConComodin1.agregarPunto(new PuntoPositivo());

        Puntaje puntajeConComodin2 = new Puntaje();
        for (int i = 0; i < 4; i++) puntajeConComodin2.agregarPunto(new PuntoPositivo());

        Puntaje puntajeConComodin3 = new Puntaje();
        for (int i = 0; i < 16; i++) puntajeConComodin3.agregarPunto(new PuntoPositivo());

        Comodin comodin1 = mock(Comodin.class); // comodin que multiplica por 2 el puntaje
        when(comodin1.aplicarComodinAPunto(puntajeSinComodines)).thenReturn(puntajeConComodin1);

        Comodin comodin2 = mock(Comodin.class); // comodin que multiplica por 2 el puntaje
        when(comodin2.aplicarComodinAPunto(puntajeConComodin1)).thenReturn(puntajeConComodin2);

        Comodin comodin3 = mock(Comodin.class); // comodin que multiplica por 4 el puntaje
        when(comodin3.aplicarComodinAPunto(puntajeConComodin2)).thenReturn(puntajeConComodin3);

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.puntajeConOpciones(opcionesSeleccionadas)).thenReturn(puntajeSinComodines);

        Jugador jugador = mock(Jugador.class);

        // testeo
        Respuesta respuesta = new Respuesta(pregunta, jugador);

        respuesta.agregarOpcion(opcion1);
        respuesta.agregarOpcion(opcion2);
        respuesta.agregarOpcion(opcion3);

        respuesta.aplicarComodin(comodin1);
        respuesta.aplicarComodin(comodin2);
        respuesta.aplicarComodin(comodin3);

        Punto puntaje = respuesta.puntaje();

        assertEquals(puntajeConComodin3, puntaje);
    }
}
