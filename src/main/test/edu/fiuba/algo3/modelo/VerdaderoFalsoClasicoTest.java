package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VerdaderoFalsoClasicoTest {

    @Test
    public void CreacionVerdaderoFalsoConSegundosNegativosLanzaMultipleChoiceError() {
        assertThrows(PreguntaError.class, () -> new VerdaderoFalsoClasico("¿Estamos en Algo 3?", -1));
    }

    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertEquals("¿Estamos en Algoritmos y programcion 3?", preguntavf.titulo());
        assertEquals(2, preguntavf.obtenerOpciones().size());
    }
    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        Jugador jugador = mock(Jugador.class);

        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        Respuesta respuestaJugador1 = new Respuesta(preguntavf, jugador);
        Respuesta respuestaJugador2 = new Respuesta(preguntavf, jugador);
        ArrayList<Opcion> opciones = preguntavf.obtenerOpciones();
        respuestaJugador1.agregarOpcion(opciones.get(0));
        respuestaJugador2.agregarOpcion(opciones.get(1));

        Integer esperadoJugador1 = 1;
        Integer esperadoJugador2 = 0;

        assertEquals(esperadoJugador1, respuestaJugador1.obtenerPuntaje());
        assertEquals(esperadoJugador2, respuestaJugador2.obtenerPuntaje());
    }

    @Test
    public void AgregarDosOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionCorrecta("Falso"));
    }

    @Test
    public void AgregarDosOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionIncorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionIncorrecta("Falso"));
    }

    @Test
    public void AgregarUnaTerceraOpcionLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionCorrecta("True"));

