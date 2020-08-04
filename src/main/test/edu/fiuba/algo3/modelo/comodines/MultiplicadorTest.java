package edu.fiuba.algo3.modelo.comodines;

import edu.fiuba.algo3.modelo.excepciones.ComodinError;
import edu.fiuba.algo3.modelo.excepciones.JugadaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionClasica;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    /*@Test
    void recibeUnJugadorYDevuelveElJugador() throws ComodinError {
        Jugador jugador = mock(Jugador.class);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.definirJugador(jugador);

        assertEquals(jugador,multiplicador.obtenerJugador());
    }

    @Test
    void recibeUnJugadorNuloYSeLanzaExcepcion() throws ComodinError {
        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () -> multiplicador.definirJugador(null));
    }*/

    @Test
    public void seValidaPreguntaConPenalidadYComodinSeGuardaEnListaDeComodinesDeJugada() throws JugadaError, RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.conPenalidad()).thenReturn(true);

        Jugador jugador = mock(Jugador.class);

        Jugada jugada = new Jugada(pregunta,jugador);

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        OpcionClasica opcionCorrecta = new OpcionClasica("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

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

        Respuesta respuesta = new Respuesta(pregunta,jugador);
        OpcionClasica opcionCorrecta = new OpcionClasica("Bien",new PuntoPositivo());
        respuesta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);

        assertThrows(ComodinError.class, () ->  multiplicador.validarPregunta(jugada));
    }

    @Test
    public void seAplicaARespuestasCorrectasYSeGuardaEnListaDeComodinesDeLasMismas() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionCorrecta = new OpcionClasica("Bien", new PuntoPositivo());

        Respuesta unaRespuestaCorrecta = new Respuesta(pregunta,jugador);
        Respuesta otraRespuestaCorrecta = new Respuesta(pregunta,jugador);

        unaRespuestaCorrecta.agregarOpcion(opcionCorrecta);
        otraRespuestaCorrecta.agregarOpcion(opcionCorrecta);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.definirJugador(jugador);
        multiplicador.aplicarARespuestas(unaRespuestaCorrecta,otraRespuestaCorrecta);

        assertEquals(1,unaRespuestaCorrecta.obtenerComodines().size());
        assertEquals(1,otraRespuestaCorrecta.obtenerComodines().size());
    }

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYSeGuardaEnListaDeComodinesDeLasMismas() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionCorrecta = new OpcionClasica("Bien",new PuntoPositivo());
        OpcionClasica opcionIncorrecta = new OpcionClasica("Mal",new PuntoNulo());

        Respuesta respuestaCorrecta = new Respuesta(pregunta,jugador);
        Respuesta respuestaIncorrecta = new Respuesta(pregunta,jugador);

        respuestaCorrecta.agregarOpcion(opcionCorrecta);
        respuestaIncorrecta.agregarOpcion(opcionIncorrecta);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.definirJugador(jugador);
        multiplicador.aplicarARespuestas(respuestaCorrecta,respuestaIncorrecta);

        assertEquals(1,respuestaCorrecta.obtenerComodines().size());
        assertEquals(1,respuestaIncorrecta.obtenerComodines().size());
    }

    @Test
    public void seAplicaARespuestasIncorrectasYSeGuardaEnListaDeComodinesDeLasMismas() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionIncorrecta = new OpcionClasica("Mal",new PuntoNegativo());

        Respuesta unaRespuestaIncorrecta = new Respuesta(pregunta,jugador);
        Respuesta otraRespuestaIncorrecta = new Respuesta(pregunta,jugador);

        unaRespuestaIncorrecta.agregarOpcion(opcionIncorrecta);
        otraRespuestaIncorrecta.agregarOpcion(opcionIncorrecta);

        Multiplicador multiplicador = new Multiplicador(2);
        multiplicador.definirJugador(jugador);
        multiplicador.aplicarARespuestas(unaRespuestaIncorrecta,otraRespuestaIncorrecta);

        assertEquals(1,unaRespuestaIncorrecta.obtenerComodines().size());
        assertEquals(1,otraRespuestaIncorrecta.obtenerComodines().size());
    }
}
