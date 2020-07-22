package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerdaderoFalsoClasicoTest {
    @Test
    public void crearConTituloElArtibutoTituloDeLaPreguntaEsElMismo() {
        VerdaderoFalsoClasico pregunta = new VerdaderoFalsoClasico("¿Estamos en Algo3?");

        assertEquals(pregunta.titulo, "¿Estamos en Algo3?");
    }

    /*
    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() {
        VerdaderoFalso preguntavf = new VerdaderoFalso("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcion("Falso");

    }

    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() {
        VerdaderoFalso preguntavf = new VerdaderoFalso("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcion("Falso");

        ArrayList<String> respuestas = new ArrayList<String>();
        respuestas.add("Verdadero");
        respuestas.add("Falso");

        ArrayList<Integer> esperado = new ArrayList<Integer>();
        esperado.add(1);
        esperado.add(0);

        assertEquals(esperado, preguntavf.obtenerPuntaje(respuestas));
    }
    */
}
