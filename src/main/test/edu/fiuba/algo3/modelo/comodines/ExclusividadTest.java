package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.util.punto.Puntaje;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void recibeUnJugadorYDevuelveElJugador() throws ComodinError {
        Jugador jugador = mock(Jugador.class);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);

        assertEquals(jugador, exclusividad.obtenerJugador());
    }

    @Test
    void recibeUnJugadorNuloYSeLanzaExcepcion() throws ComodinError {
        Exclusividad exclusividad = new Exclusividad(2);

        assertThrows(ComodinError.class, () -> exclusividad.definirJugador(null));
    }

    @Test
    public void seValidaPreguntaSinPenalidadYComodinSeGuardaEnListaDeComodinesDeJugada() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(false);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Respuesta respuesta = new Respuesta(pregunta, jugador);
        OpcionClasica opcionCorrecta = new OpcionClasica("Bien", new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.validarPregunta(jugada);

        assertEquals(1, jugada.obtenerComodines().size());
    }

    @Test
    public void seValidaPreguntaConPenalidadYSeLanzaExcepcion() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(true);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta, jugador);

        Respuesta respuesta = new Respuesta(pregunta, jugador);
        OpcionClasica opcionCorrecta = new OpcionClasica("Bien", new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Exclusividad exclusividad = new Exclusividad(2);

        assertThrows(ComodinError.class, () -> exclusividad.validarPregunta(jugada));
    }

    @Test
    public void seAplicaARespuestasCorrectasYNoSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionCorrecta = new OpcionClasica("Bien",new PuntoPositivo());

        Respuesta unaRespuestaCorrecta = new Respuesta(pregunta,jugador);
        Respuesta otraRespuestaCorrecta = new Respuesta(pregunta,jugador);

        unaRespuestaCorrecta.agregarOpcion(opcionCorrecta);
        otraRespuestaCorrecta.agregarOpcion(opcionCorrecta);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);

        exclusividad.aplicarARespuestas(unaRespuestaCorrecta,otraRespuestaCorrecta);

        assertEquals(0,unaRespuestaCorrecta.obtenerComodines().size());
        assertEquals(0,otraRespuestaCorrecta.obtenerComodines().size());
    }

    /*@Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionCorrecta = new OpcionClasica("Bien",new PuntoPositivo());
        OpcionClasica opcionIncorrecta = new OpcionClasica("Mal",new PuntoNulo());

        ArrayList<Opcion> opcionesCorrectas = new ArrayList<>();
        opcionesCorrectas.add(opcionCorrecta);

        Respuesta unaRespuestaCorrecta = new Respuesta(pregunta,jugador);
        Respuesta unaRespuestaIncorrecta = new Respuesta(pregunta,jugador);

        unaRespuestaCorrecta.agregarOpcion(opcionCorrecta);
        unaRespuestaIncorrecta.agregarOpcion(opcionIncorrecta);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);
        exclusividad.aplicarARespuestas(unaRespuestaCorrecta,unaRespuestaIncorrecta);

        assertEquals(1,unaRespuestaCorrecta.obtenerComodines().size());
        assertEquals(1,unaRespuestaIncorrecta.obtenerComodines().size());
    }*/

    @Test
    public void seAplicaARespuestasIncorrectasYNoSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionIncorrecta = new OpcionClasica("Mal",new PuntoNulo());

        Respuesta unaRespuestaIncorrecta = new Respuesta(pregunta,jugador);
        Respuesta otraRespuestaIncorrecta = new Respuesta(pregunta,jugador);

        unaRespuestaIncorrecta.agregarOpcion(opcionIncorrecta);
        otraRespuestaIncorrecta.agregarOpcion(opcionIncorrecta);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);
        exclusividad.aplicarARespuestas(unaRespuestaIncorrecta,otraRespuestaIncorrecta);

        assertEquals(0,unaRespuestaIncorrecta.obtenerComodines().size());
        assertEquals(0,otraRespuestaIncorrecta.obtenerComodines().size());
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

