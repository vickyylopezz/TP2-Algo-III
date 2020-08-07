package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleChoiceParcialTest {

    @Test
    public void CreacionDeMultipleChoiceParcialIndicandoRespuestaCorrecta() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCP.obtenerTitulo());
        assertEquals(4, preguntaMCP.obtenerOpciones().size());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void MultipleChoiceParcialAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas1 = new ArrayList<>();
        opcionesElegidas1.add(opciones.get(0));
        opcionesElegidas1.add(opciones.get(1));

        ArrayList<Opcion> opcionesElegidas2 = new ArrayList<>();
        opcionesElegidas2.add(opciones.get(0));
        opcionesElegidas2.add(opciones.get(2));

        ArrayList<Opcion> opcionesElegidas3 = new ArrayList<>();
        opcionesElegidas3.add(opciones.get(1));

        Integer esperadoJugador1 = 2;
        Integer esperadoJugador2 = 0;
        Integer esperadoJugador3 = 1;

        assertEquals(esperadoJugador1, preguntaMCP.puntajeConOpciones(opcionesElegidas1).obtenerValor());
        assertEquals(esperadoJugador2, preguntaMCP.puntajeConOpciones(opcionesElegidas2).obtenerValor());
        assertEquals(esperadoJugador3, preguntaMCP.puntajeConOpciones(opcionesElegidas3).obtenerValor());
    }

    @Test
    public void AgregarMasDeCincoOpcionesLanzaUnPreguntaError() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?");
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");
        preguntaMCP.agregarOpcionCorrecta("Juan");

        assertThrows(PreguntaError.class, () -> preguntaMCP.agregarOpcionIncorrecta("Nicanor"));
        assertThrows(PreguntaError.class, () -> preguntaMCP.agregarOpcionCorrecta("Celeste"));
    }

    @Test
    public void ObtenerPuntajeConOpcionesDeUnArregloVacioDevuelveCero() {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Cuál es el apellido de nuestro corrector?");
        ArrayList<Opcion> opciones = new ArrayList<>();

        assertEquals(0, preguntaMCP.puntajeConOpciones(opciones).obtenerValor());
    }
}
