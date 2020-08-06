package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ExclusividadTest {

    @Test
    public void seCreaExclusividadConFactorNuloYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Exclusividad(0));
    }

    @Test
    public void seCreaExclusividadConFactorNegativoYSeLanzaExcepcion() {

        assertThrows(ComodinError.class, () -> new Exclusividad(-1));
    }

    @Test
    public void seCreaExclusividadConFactorPositivoYDevuelveElFactor() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        assertEquals(2, exclusividad.obtenerFactor());
    }

    @Test
    public void recibeUnJugadorYDevuelveElJugador() throws ComodinError {
        Jugador jugador = mock(Jugador.class);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);

        assertEquals(jugador, exclusividad.obtenerJugador());
    }

    @Test
    public void recibeUnJugadorNuloYSeLanzaExcepcion() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        assertThrows(ComodinError.class, () -> exclusividad.definirJugador(null));
    }

    @Test
    public void seValidaPreguntaSinPenalidadYComodinSeGuardaEnListaDeComodinesDeJugada() throws ComodinError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(false);
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = new Jugador("Tomas");
        jugador.agregarComodin(exclusividad);
        Jugada jugada = new Jugada(pregunta, jugador);

        jugada.seleccionarComodin(exclusividad);

        assertEquals(Exclusividad.class, jugada.comodinSeleccionado().getClass());
    }

    @Test
    public void seValidaPreguntaConPenalidadYSeLanzaExcepcion() throws ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(true);

        Jugador jugador = mock(Jugador.class);
        Jugada jugada = new Jugada(pregunta, jugador);

        Exclusividad exclusividad = new Exclusividad(2);

        assertThrows(ComodinError.class, () -> exclusividad.validarPregunta(jugada));
    }

    @Test
    public void seAplicaARespuestasCorrectasYNoSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError, JugadorError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta unaRespuestaCorrecta = mock(Respuesta.class);
        when(unaRespuestaCorrecta.esCorrecta()).thenReturn(true);
        when(unaRespuestaCorrecta.validarComodin(exclusividad)).thenReturn(true);

        Respuesta otraRespuestaCorrecta = mock(Respuesta.class);
        when(otraRespuestaCorrecta.esCorrecta()).thenReturn(true);
        when(otraRespuestaCorrecta.validarComodin(exclusividad)).thenReturn(true);

        exclusividad.aplicarARespuestas(unaRespuestaCorrecta, otraRespuestaCorrecta);

        verify(unaRespuestaCorrecta, times(0)).aplicarComodin(exclusividad);
        verify(otraRespuestaCorrecta, times(0)).aplicarComodin(exclusividad);
    }

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError, JugadorError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta respuestaCorrecta = mock(Respuesta.class);
        when(respuestaCorrecta.esCorrecta()).thenReturn(true);
        when(respuestaCorrecta.validarComodin(exclusividad)).thenReturn(true);

        Respuesta respuestaIncorrecta = mock(Respuesta.class);
        when(respuestaIncorrecta.esCorrecta()).thenReturn(false);
        when(respuestaIncorrecta.validarComodin(exclusividad)).thenReturn(true);

        exclusividad.aplicarARespuestas(respuestaCorrecta, respuestaIncorrecta);

        verify(respuestaCorrecta, times(1)).aplicarComodin(exclusividad);
        verify(respuestaIncorrecta, times(1)).aplicarComodin(exclusividad);
    }

    @Test
    public void seAplicaARespuestasIncorrectasYNoSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError, JugadorError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta unaRespuestaIncorrecta = mock(Respuesta.class);
        when(unaRespuestaIncorrecta.esCorrecta()).thenReturn(true);
        when(unaRespuestaIncorrecta.validarComodin(exclusividad)).thenReturn(true);

        Respuesta otraRespuestaIncorrecta = mock(Respuesta.class);
        when(otraRespuestaIncorrecta.esCorrecta()).thenReturn(true);
        when(otraRespuestaIncorrecta.validarComodin(exclusividad)).thenReturn(true);

        exclusividad.aplicarARespuestas(unaRespuestaIncorrecta,otraRespuestaIncorrecta);

        verify(unaRespuestaIncorrecta, times(0)).aplicarComodin(exclusividad);
        verify(otraRespuestaIncorrecta, times(0)).aplicarComodin(exclusividad);
    }

    @Test
    public void recibeUnPuntajeNuloYSeLanzaExcepcion() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.puntajeNuevo(null));
    }

    @Test
    public void recibeUnPuntajeConPuntosNulosYDevuelvePuntajeConPuntosConValorCero() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNulo());

        Exclusividad exclusividad = new Exclusividad(2);
        Puntaje puntajeNuevo = exclusividad.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(0,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void recibeUnPuntajeConPuntosPositivosYDevuelvePuntajeConPuntosPositivosConValorDos() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Exclusividad exclusividad = new Exclusividad(2);
        Puntaje puntajeNuevo = exclusividad.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(2,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void recibeUnPuntajeConPuntosNegativosYDevuelvePuntajeConPuntosNegativosConValorDos() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Exclusividad exclusividad = new Exclusividad(2);
        Puntaje puntajeNuevo = exclusividad.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(-2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(-2,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void recibeUnPuntajeConUnPuntoPositivoYOtroNegativo() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());

        Exclusividad exclusividad = new Exclusividad(2);
        Puntaje puntajeNuevo = exclusividad.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(-2,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void recibeUnPuntajeConUnPuntoPositivoYOtroNulo() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNulo());

        Exclusividad exclusividad = new Exclusividad(2);
        Puntaje puntajeNuevo = exclusividad.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }

    @Test
    public void recibeUnPuntajeConUnPuntoNegativoYOtroNulo() throws ComodinError {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());

        Exclusividad exclusividad = new Exclusividad(2);
        Puntaje puntajeNuevo = exclusividad.puntajeNuevo(puntaje.obtenerPuntos());

        assertEquals(-2,puntajeNuevo.obtenerPuntos().get(0).obtenerValor());
        assertEquals(0,puntajeNuevo.obtenerPuntos().get(1).obtenerValor());
    }
}

