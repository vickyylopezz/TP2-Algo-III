package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class MultipleChoiseParcialTest {

    @Test
    public void CreacionDeMultipleChoiseConSegundosNegativosLanzaPreguntaError(){
        assertThrows(PreguntaError.class, () -> new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?", -1));
    }

    @Test
    public void CreacionDeMultipleChoiseParcialIndicandoRespuestaCorrecta() throws PreguntaError {
        MultipleChoiseParcial preguntaMCP = new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCP.titulo());
        assertEquals(4, preguntaMCP.obtenerOpciones().size());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiseParcial preguntaMCP = new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void MultipleChoiseParcialAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PreguntaError {
        MultipleChoiseParcial preguntaMCP = new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        Jugador jugador = mock(Jugador.class);

        Respuesta respuestaJugador1 = new Respuesta(preguntaMCP, jugador);
        Respuesta respuestaJugador2 = new Respuesta(preguntaMCP, jugador);
        Respuesta respuestaJugador3 = new Respuesta(preguntaMCP, jugador);
        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        respuestaJugador1.agregarOpcion(opciones.get(0));
        respuestaJugador1.agregarOpcion(opciones.get(1));
        respuestaJugador2.agregarOpcion(opciones.get(0));
        respuestaJugador2.agregarOpcion(opciones.get(2));
        respuestaJugador3.agregarOpcion(opciones.get(1));

        Integer esperadoJugador1 = 2;
        Integer esperadoJugador2 = 0;
        Integer esperadoJugador3 = 1;

        assertEquals(esperadoJugador1, respuestaJugador1.obtenerPuntaje());
        assertEquals(esperadoJugador2, respuestaJugador2.obtenerPuntaje());
        assertEquals(esperadoJugador3, respuestaJugador3.obtenerPuntaje());
    }

    @Test
    public void AgregarMasDeCincoOpcionesLanzaUnPreguntaError() throws PreguntaError {
        MultipleChoiseParcial preguntaMCP = new MultipleChoiseParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");
        preguntaMCP.agregarOpcionCorrecta("Juan");

        assertThrows(PreguntaError.class, () -> preguntaMCP.agregarOpcionIncorrecta("Nicanor"));
    }
}
