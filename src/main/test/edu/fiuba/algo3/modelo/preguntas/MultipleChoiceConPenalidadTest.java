package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceConPenalidad;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class MultipleChoiceConPenalidadTest {

    @Test
    public void CreacionDeMultipleChoiceConPenalidadIndicandoRespuestaCorrecta() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?");
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCCP.obtenerTitulo());
        assertEquals(4, preguntaMCCP.obtenerOpciones().size());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?");
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCCP.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void MultipleChoiceConPenalidadAsignaPuntosCorrectamenteADiferentesRespuestas() throws PreguntaError, RespuestaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?");
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCCP.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas1 = new ArrayList<>();
        opcionesElegidas1.add(opciones.get(0));
        opcionesElegidas1.add(opciones.get(1));

        ArrayList<Opcion> opcionesElegidas2 = new ArrayList<>();
        opcionesElegidas2.add(opciones.get(0));
        opcionesElegidas2.add(opciones.get(1));
        opcionesElegidas2.add(opciones.get(2));

        ArrayList<Opcion> opcionesElegidas3 = new ArrayList<>();
        opcionesElegidas3.add(opciones.get(1));

        ArrayList<Opcion> opcionesElegidas4 = new ArrayList<>();
        opcionesElegidas4.add(opciones.get(2));
        opcionesElegidas4.add(opciones.get(3));

        Integer esperadoJugador1 = 2;
        Integer esperadoJugador2 = 1;
        Integer esperadoJugador3 = 1;
        Integer esperadoJugador4 = -2;

        assertEquals(esperadoJugador1, preguntaMCCP.puntajeConOpciones(opcionesElegidas1).obtenerValor());
        assertEquals(esperadoJugador2, preguntaMCCP.puntajeConOpciones(opcionesElegidas2).obtenerValor());
        assertEquals(esperadoJugador3, preguntaMCCP.puntajeConOpciones(opcionesElegidas3).obtenerValor());
        assertEquals(esperadoJugador4, preguntaMCCP.puntajeConOpciones(opcionesElegidas4).obtenerValor());
    }

    @Test
    public void AgregarMasDeCincoOpcionesLanzaUnPreguntaError() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?");
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");
        preguntaMCCP.agregarOpcionCorrecta("Juan");

        assertThrows(PreguntaError.class, () -> preguntaMCCP.agregarOpcionIncorrecta("Nicanor"));
        assertThrows(PreguntaError.class, () -> preguntaMCCP.agregarOpcionCorrecta("Celeste"));
    }

    @Test
    public void ObtenerPuntajeConOpcionesDeUnArregloVacioDevuelveCero() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Cuál es el apellido de nuestro corrector?");
        ArrayList<Opcion> opciones = new ArrayList<Opcion>();

        assertEquals(0, preguntaMCCP.puntajeConOpciones(opciones).obtenerValor());
    }

}

