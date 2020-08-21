package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.jugador.JugadorError;
import edu.fiuba.algo3.modelo.excepciones.comodin.ComodinError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

        Exclusividad exclusividad = new Exclusividad(2);

        assertThrows(ComodinError.class, () -> exclusividad.validarPregunta(pregunta));
    }

    @Test
    public void seAplicaARespuestasCorrectasYNoSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta unaRespuestaCorrecta = mock(Respuesta.class);
        when(unaRespuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta otraRespuestaCorrecta = mock(Respuesta.class);
        when(otraRespuestaCorrecta.esCorrecta()).thenReturn(true);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(unaRespuestaCorrecta);
        respuestas.add(otraRespuestaCorrecta);

        exclusividad.aplicarARespuestas(respuestas);

        verify(unaRespuestaCorrecta, times(0)).aplicarComodin(exclusividad);
        verify(otraRespuestaCorrecta, times(0)).aplicarComodin(exclusividad);
    }

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta respuestaCorrecta = mock(Respuesta.class);
        when(respuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta respuestaIncorrecta = mock(Respuesta.class);
        when(respuestaIncorrecta.esCorrecta()).thenReturn(false);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(respuestaCorrecta);
        respuestas.add(respuestaIncorrecta);

        exclusividad.aplicarARespuestas(respuestas);

        verify(respuestaCorrecta, times(1)).aplicarComodin(exclusividad);
    }

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYNoSeGuardaEnListaDeComodinesDeLaRespuestaInorrecta() throws ComodinError, PuntoError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta respuestaCorrecta = mock(Respuesta.class);
        when(respuestaCorrecta.esCorrecta()).thenReturn(true);

        Respuesta respuestaIncorrecta = mock(Respuesta.class);
        when(respuestaIncorrecta.esCorrecta()).thenReturn(false);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(respuestaCorrecta);
        respuestas.add(respuestaIncorrecta);

        exclusividad.aplicarARespuestas(respuestas);

        verify(respuestaIncorrecta, times(0)).aplicarComodin(exclusividad);
    }

    @Test
    public void seAplicaARespuestasIncorrectasYNoSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        Jugador jugador = mock(Jugador.class);
        exclusividad.definirJugador(jugador);

        Respuesta unaRespuestaIncorrecta = mock(Respuesta.class);
        when(unaRespuestaIncorrecta.esCorrecta()).thenReturn(false);

        Respuesta otraRespuestaIncorrecta = mock(Respuesta.class);
        when(otraRespuestaIncorrecta.esCorrecta()).thenReturn(false);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(unaRespuestaIncorrecta);
        respuestas.add(otraRespuestaIncorrecta);

        exclusividad.aplicarARespuestas(respuestas);

        verify(unaRespuestaIncorrecta, times(0)).aplicarComodin(exclusividad);
        verify(otraRespuestaIncorrecta, times(0)).aplicarComodin(exclusividad);
    }

    @Test
    public void recibeUnPunToNuloYAplicaComodinAlPunto() throws ComodinError{
        Exclusividad exclusividad = new Exclusividad(2);
        Punto puntoNuevo = exclusividad.aplicarComodinAPunto(new PuntoNulo());

        assertEquals(0,puntoNuevo.obtenerValor());
    }

    @Test
    public void recibeUnPuntoPositivoYAplicaComodinAlPunto() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);
        Punto puntoNuevo = exclusividad.aplicarComodinAPunto(new PuntoPositivo());

        assertEquals(2,puntoNuevo.obtenerValor());
    }

    @Test
    public void recibeUnPuntoNegativoYAplicaComodinAlPunto() throws ComodinError{
        Exclusividad exclusividad = new Exclusividad(2);
        Punto puntoNuevo = exclusividad.aplicarComodinAPunto(new PuntoNegativo());

        assertEquals(-2,puntoNuevo.obtenerValor());
    }
}

