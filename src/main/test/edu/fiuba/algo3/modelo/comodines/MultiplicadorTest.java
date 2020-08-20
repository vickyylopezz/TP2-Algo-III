package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.*;
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
    public void seValidaPreguntaConPenalidadYComodinSeGuardaEnListaDeComodinesDeJugada() throws ComodinError, JugadorError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(true);
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador jugador = new Jugador("Juan");
        jugador.agregarComodin(multiplicador);
        Jugada jugada = new Jugada(pregunta,jugador);

        jugada.seleccionarComodin(multiplicador);

        assertEquals(Multiplicador.class, jugada.comodinSeleccionado().getClass());
    }

    @Test
    public void seValidaPreguntaSinPenalidadYSeLanzaExcepcion() throws ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(false);

        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.validarPregunta(pregunta));
    }

    @Test
    public void seAplicaARespuestasCorrectasYSeGuardaEnListaDeComodinesDeLaRespuestaConMismoJugadorQueElComodin() throws ComodinError, PuntoError {
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        multiplicador.definirJugador(unJugador);

        Respuesta unaRespuestaCorrecta = mock(Respuesta.class);
        when(unaRespuestaCorrecta.obtenerJugador()).thenReturn(unJugador);
        when(unaRespuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta otraRespuestaCorrecta = mock(Respuesta.class);
        when(otraRespuestaCorrecta.obtenerJugador()).thenReturn(otroJugador);
        when(otraRespuestaCorrecta.esCorrecta()).thenReturn(true);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(unaRespuestaCorrecta);
        respuestas.add(otraRespuestaCorrecta);

        multiplicador.aplicarARespuestas(respuestas);

        verify(unaRespuestaCorrecta, times(1)).aplicarComodin(multiplicador);
        verify(otraRespuestaCorrecta, times(0)).aplicarComodin(multiplicador);
    }

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYDeLaRespuestaConMismoJugadorQueElComodin() throws ComodinError, PuntoError {
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        multiplicador.definirJugador(unJugador);

        Respuesta respuestaCorrecta = mock(Respuesta.class);
        when(respuestaCorrecta.obtenerJugador()).thenReturn(unJugador);
        when(respuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta respuestaIncorrecta = mock(Respuesta.class);
        when(respuestaIncorrecta.obtenerJugador()).thenReturn(otroJugador);
        when(respuestaIncorrecta.esCorrecta()).thenReturn(false);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(respuestaCorrecta);
        respuestas.add(respuestaIncorrecta);

        multiplicador.aplicarARespuestas(respuestas);

        verify(respuestaCorrecta, times(1)).aplicarComodin(multiplicador);
        verify(respuestaIncorrecta, times(0)).aplicarComodin(multiplicador);
    }

    @Test
    public void seAplicaARespuestasIncorrectasYSeGuardaEnListaDeComodinesDeLaRespuestaConMismoJugadorQueElComodin() throws ComodinError, PuntoError {
        Multiplicador multiplicador = new Multiplicador(2);

        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        multiplicador.definirJugador(unJugador);

        Respuesta unaRespuestaIncorrecta = mock(Respuesta.class);
        when(unaRespuestaIncorrecta.obtenerJugador()).thenReturn(unJugador);
        when(unaRespuestaIncorrecta.esCorrecta()).thenReturn(true);

        Respuesta otraRespuestaIncorrecta = mock(Respuesta.class);
        when(otraRespuestaIncorrecta.obtenerJugador()).thenReturn(otroJugador);
        when(otraRespuestaIncorrecta.esCorrecta()).thenReturn(true);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(unaRespuestaIncorrecta);
        respuestas.add(otraRespuestaIncorrecta);

        multiplicador.aplicarARespuestas(respuestas);

        verify(unaRespuestaIncorrecta, times(1)).aplicarComodin(multiplicador);
        verify(otraRespuestaIncorrecta, times(0)).aplicarComodin(multiplicador);
    }

    @Test
    public void recibeUnPunToNuloYAplicaComodinAlPunto() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);
        Punto puntoNuevo = multiplicador.aplicarComodinAPunto(new PuntoNulo());

        assertEquals(0,puntoNuevo.obtenerValor());
    }

    @Test
    public void recibeUnPuntoPositivoYAplicaComodinAlPunto() throws ComodinError{
        Multiplicador multiplicador = new Multiplicador(2);
        Punto puntoNuevo = multiplicador.aplicarComodinAPunto(new PuntoPositivo());

        assertEquals(2,puntoNuevo.obtenerValor());
    }

    @Test
    public void recibeUnPuntoNegativoYAplicaComodinAlPunto() throws ComodinError{
        Multiplicador multiplicador = new Multiplicador(2);
        Punto puntoNuevo = multiplicador.aplicarComodinAPunto(new PuntoNegativo());

        assertEquals(-2,puntoNuevo.obtenerValor());
    }
}
