package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class VerdaderoFalsoClasicoTest {
    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");
        
        assertEquals("¿Estamos en Algoritmos y programcion 3?", preguntavf.titulo());
    }
    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws RespuestaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        Jugador jugador = mock(Jugador.class);

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        Respuesta respuestaJugador1 = new Respuesta(preguntavf, jugador);
        Respuesta respuestaJugador2 = new Respuesta(preguntavf, jugador);
        ArrayList<Opcion> opciones = preguntavf.obtenerOpciones();
        respuestaJugador1.agregarOpcion(opciones.get(0));
        respuestaJugador2.agregarOpcion(opciones.get(1));
        respuestas.add(respuestaJugador1);
        respuestas.add(respuestaJugador2);

        ArrayList<Integer> esperado = new ArrayList<>();
        esperado.add(1);
        esperado.add(0);

        assertEquals(esperado, preguntavf.puntajeConRespuestas(respuestas));
    }
}

