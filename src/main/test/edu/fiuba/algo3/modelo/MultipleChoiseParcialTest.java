package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipleChoiseParcialTest {
    @Test
    public void CreacionDeMultipleChoiseParcialIndicandoRespuestaCorrecta(){
        MultipleChoiseParcial preguntaMCP = new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCP.titulo());
    }

    @Test
    public void MultipleChoiseParcialAsignaPuntosCorrectamenteAUnaListaDeRespuestas(){
        MultipleChoiseParcial preguntaMCP = new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> respuestasJugador1 = new ArrayList<Opcion>();
        ArrayList<Opcion> respuestasJugador2 = new ArrayList<Opcion>();
        ArrayList<Opcion> respuestasJugador3 = new ArrayList<Opcion>();
        ArrayList<ArrayList<Opcion>> todasLasRespuestas = new ArrayList<ArrayList<Opcion>>();
        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        respuestasJugador1.add(opciones.get(0));
        respuestasJugador1.add(opciones.get(1));
        respuestasJugador2.add(opciones.get(0));
        respuestasJugador2.add(opciones.get(2));
        respuestasJugador3.add(opciones.get(1));

        todasLasRespuestas.add(respuestasJugador1);
        todasLasRespuestas.add(respuestasJugador2);
        todasLasRespuestas.add(respuestasJugador3);

        ArrayList<Integer> esperado = new ArrayList<Integer>();
        esperado.add(2);
        esperado.add(0);
        esperado.add(1);

        assertEquals(esperado, preguntaMCP.obtenerPuntajes(todasLasRespuestas));
    }
}