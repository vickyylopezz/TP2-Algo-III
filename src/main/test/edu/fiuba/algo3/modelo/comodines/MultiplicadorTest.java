package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.util.punto.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MultiplicadorTest {
    @Test
    public void seCreaMultiplicadorConJugadorNuloYSeClanzaExcepcion(){
        assertThrows(ComodinError.class, () -> new Multiplicador(0));
    }
    @Test
    public void seCreaMultiplicadorConFactorNuloYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Multiplicador(0));
    }

    @Test
    public void seCreaMultiplicadorConFactorNegativoYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Multiplicador(-1));
    }

    @Test
    public void seCreaMultiplicadorConFactorPositivoYDevuelveElFactor() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertEquals(2,multiplicador.obtenerFactor());
    }

    @Test
    public void recibeUnJugadorYDevuelveElJugador() throws ComodinError {
        Jugador jugador = mock(Jugador.class);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.definirJugador(jugador);

        assertEquals(jugador,multiplicador.obtenerJugador());
    }

    @Test
    public void recibeUnJugadorNuloYSeLanzaExcepcion() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () -> multiplicador.definirJugador(null));
    }

    @Test
    public void seValidaPreguntaConPenalidadYComodinSeGuardaEnListaDeComodinesDeJugada() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(true);

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta,jugador);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.validarPregunta(jugada);

        assertEquals(1,jugada.obtenerComodines().size());
    }

    @Test
    public void seValidaPreguntaSinPenalidadYSeLanzaExcepcion() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(false);

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta,jugador);

        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.validarPregunta(jugada));
    }

    @Test
    public void seAplicaARespuestasCorrectasYSeGuardaEnListaDeComodinesDeLasMismas() throws RespuestaError, ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador jugador = mock(Jugador.class);
        multiplicador.definirJugador(jugador);

        Respuesta unaRespuestaCorrecta = mock(Respuesta.class);
        when(unaRespuestaCorrecta.obtenerJugador()).thenReturn(jugador);
        when(unaRespuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta otraRespuestaCorrecta = mock(Respuesta.class);
        when(otraRespuestaCorrecta.obtenerJugador()).thenReturn(jugador);
        when(otraRespuestaCorrecta.esCorrecta()).thenReturn(true);

        multiplicador.aplicarARespuestas(unaRespuestaCorrecta, otraRespuestaCorrecta);

        verify(unaRespuestaCorrecta, times(1)).aplicarComodin(multiplicador);
        verify(otraRespuestaCorrecta, times(1)).aplicarComodin(multiplicador);
    }

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYSeGuardaEnListaDeComodinesDeLasMismas() throws RespuestaError, ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador jugador = mock(Jugador.class);
        multiplicador.definirJugador(jugador);

        Respuesta respuestaCorrecta = mock(Respuesta.class);
        when(respuestaCorrecta.obtenerJugador()).thenReturn(jugador);
        when(respuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta respuestaIncorrecta = mock(Respuesta.class);
        when(respuestaIncorrecta.obtenerJugador()).thenReturn(jugador);
        when(respuestaIncorrecta.esCorrecta()).thenReturn(false);

        multiplicador.aplicarARespuestas(respuestaCorrecta, respuestaIncorrecta);

        verify(respuestaCorrecta, times(1)).aplicarComodin(multiplicador);
        verify(respuestaIncorrecta, times(1)).aplicarComodin(multiplicador);
    }

    @Test
    public void seAplicaARespuestasIncorrectasYSeGuardaEnListaDeComodinesDeLasMismas() throws RespuestaError, ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador jugador = mock(Jugador.class);
        multiplicador.definirJugador(jugador);

        Respuesta unaRespuestaIncorrecta = mock(Respuesta.class);
        when(unaRespuestaIncorrecta.obtenerJugador()).thenReturn(jugador);
        when(unaRespuestaIncorrecta.esCorrecta()).thenReturn(true);

        Respuesta otraRespuestaIncorrecta = mock(Respuesta.class);
        when(otraRespuestaIncorrecta.obtenerJugador()).thenReturn(jugador);
        when(otraRespuestaIncorrecta.esCorrecta()).thenReturn(true);

        multiplicador.aplicarARespuestas(unaRespuestaIncorrecta, otraRespuestaIncorrecta);

        verify(unaRespuestaIncorrecta, times(1)).aplicarComodin(multiplicador);
        verify(otraRespuestaIncorrecta, times(1)).aplicarComodin(multiplicador);
    }

    @Test
    public void recibeUnPuntajeNuloYSeLanzaExcepcion() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.puntajeNuevo(null));
    }

    @Test
    public void multiplicadorDeFactorDosRecibeUnPuntajeConPuntosNulosYDevuelvePuntajeConPuntosConValorCero() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNulo());

        Multiplicador multiplicador = new Multiplicador(2);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(0,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorDosRecibeUnPuntajeConPuntosPositivosYDevuelvePuntajeConPuntosPositivosConValorDos() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Multiplicador multiplicador = new Multiplicador(2);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(2,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorDosRecibeUnPuntajeConPuntosNegativosYDevuelvePuntajeConPuntosNegativosConValorDos() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Multiplicador multiplicador = new Multiplicador(2);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(-2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(-2,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorTresRecibeUnPuntajeConPuntosNulosYDevuelvePuntajeConPuntosConValorCero() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNulo());

        Multiplicador multiplicador = new Multiplicador(3);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(0,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorTresRecibeUnPuntajeConPuntosPositivosYDevuelvePuntajeConPuntosPositivosConValorTres() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Multiplicador multiplicador = new Multiplicador(3);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(3,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(3,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorTresRecibeUnPuntajeConPuntosNegativosYDevuelvePuntajeConPuntosNegativosConValorTres() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Multiplicador multiplicador = new Multiplicador(3);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(-3,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(-3,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorTresRecibeUnPuntajeConUnPuntoPositivoYOtroNegativo() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());

        Multiplicador multiplicador = new Multiplicador(3);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(3,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(-3,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorTresRecibeUnPuntajeConUnPuntoPositivoYOtroNulo() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNulo());

        Multiplicador multiplicador = new Multiplicador(3);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(3,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void multiplicadorDeFactorTresRecibeUnPuntajeConUnPuntoNegativoYOtroNulo() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());

        Multiplicador multiplicador = new Multiplicador(3);
        Puntaje puntajeNuevo = multiplicador.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(-3,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void aplicarComodinARespuestaNulaLanzaComodinError() throws ComodinError, PreguntaError, RespuestaError {
        Multiplicador multiplicador = new Multiplicador(3);
        VerdaderoFalsoClasico pregunta = new VerdaderoFalsoClasico("¿Estamso en 2020?");
        pregunta.agregarOpcionCorrecta("Veradero");
        pregunta.agregarOpcionIncorrecta("Falso");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        Respuesta respuesta = new Respuesta(pregunta, null);
        respuesta.agregarOpcion(opciones.get(0));

        assertThrows(ComodinError.class, () -> multiplicador.aplicarARespuestas(null, respuesta));
        assertThrows(ComodinError.class, () -> multiplicador.aplicarARespuestas(respuesta, null));
    }

}
