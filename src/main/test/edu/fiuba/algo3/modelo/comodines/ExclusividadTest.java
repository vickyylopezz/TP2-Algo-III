package edu.fiuba.algo3.modelo.comodines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ExclusividadTest {
    /*
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

    @Test
    public void seAplicaAUnaRespuestaCorrectaYAOtraIncorrectaYSeGuardaEnListaDeComodinesDeLaRespuestaCorrecta() throws RespuestaError, ComodinError {
        Pregunta pregunta = mock(Pregunta.class);

        Jugador jugador = mock(Jugador.class);

        OpcionClasica opcionCorrecta = new OpcionClasica("Bien",new PuntoPositivo());
        OpcionClasica opcionIncorrecta = new OpcionClasica("Mal",new PuntoNulo());

        ArrayList<Opcion> opcionesCorrectas = new ArrayList<>();
        opcionesCorrectas.add(opcionCorrecta);

        when(pregunta.obtenerOpcionesCorrectas()).thenReturn(opcionesCorrectas);

        Respuesta unaRespuestaCorrecta = new Respuesta(pregunta,jugador);
        Respuesta unaRespuestaIncorrecta = new Respuesta(pregunta,jugador);

        unaRespuestaCorrecta.agregarOpcion(opcionCorrecta);
        unaRespuestaIncorrecta.agregarOpcion(opcionIncorrecta);

        Exclusividad exclusividad = new Exclusividad(2);
        exclusividad.definirJugador(jugador);
        exclusividad.aplicarARespuestas(unaRespuestaCorrecta,unaRespuestaIncorrecta);

        assertEquals(1,unaRespuestaCorrecta.obtenerComodines().size());
        assertEquals(1,unaRespuestaIncorrecta.obtenerComodines().size());
    }

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
    */
}

