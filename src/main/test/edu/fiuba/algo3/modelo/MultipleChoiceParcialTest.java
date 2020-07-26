package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class MultipleChoiceParcialTest {
    @Test
    public void CreacionDeMultipleChoiseParcialIndicandoRespuestaCorrecta(){
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCP.titulo());
    }

    @Test
    public void MultipleChoiseParcialAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws RespuestaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        Jugador jugador = mock(Jugador.class);
        Pregunta pregunta = mock(Pregunta.class);

        Respuesta respuestaJugador1 = new Respuesta(pregunta, jugador);
        Respuesta respuestaJugador2 = new Respuesta(pregunta, jugador);
        Respuesta respuestaJugador3 = new Respuesta(pregunta, jugador);
        ArrayList<Respuesta> todasLasRespuestas = new ArrayList<Respuesta>();
        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        respuestaJugador1.agregarOpcion(opciones.get(0));
        respuestaJugador1.agregarOpcion(opciones.get(1));
        respuestaJugador2.agregarOpcion(opciones.get(0));
        respuestaJugador2.agregarOpcion(opciones.get(2));
        respuestaJugador3.agregarOpcion(opciones.get(1));

        todasLasRespuestas.add(respuestaJugador1);
        todasLasRespuestas.add(respuestaJugador2);
        todasLasRespuestas.add(respuestaJugador3);

        ArrayList<Integer> esperado = new ArrayList<Integer>();
        esperado.add(2);
        esperado.add(0);
        esperado.add(1);

        assertEquals(esperado, preguntaMCP.puntajeConRespuestas(todasLasRespuestas));
    }
}
