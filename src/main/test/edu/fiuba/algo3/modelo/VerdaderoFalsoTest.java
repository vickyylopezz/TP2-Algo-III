package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerdaderoFalsoTest {
    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() {
        Pregunta preguntavf = new VerdaderoFalso("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarRespuestaCorrecta("Verdadero");
        preguntavf.agregarOpcion("Falso");

    }
    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() {
        Pregunta preguntavf = new VerdaderoFalso("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarRespuestaCorrecta("Verdadero");
        preguntavf.agregarOpcion("Falso");

        ArrayList<String> respuestas = new ArrayList<String>();
        respuestas.add("Verdadero");
        respuestas.add("Falso");

        ArrayList<Integer> esperado = new ArrayList<Integer>();
        esperado.add(1);
        esperado.add(0);

        assertEquals(esperado, preguntavf.obtenerPuntaje(respuestas));
    }
}