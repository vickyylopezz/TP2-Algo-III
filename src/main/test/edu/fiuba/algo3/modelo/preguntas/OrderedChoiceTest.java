package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class OrderedChoiceTest {

    @Test
    public void CreacionDePreguntaOrderedChoice() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");

        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        // La respuesta correcta estará compuesta por las opciones en el orden en que se agregaron.

        assertEquals("Ordenar de menor a mayor", pregunta.obtenerTitulo());
        assertEquals(3, pregunta.obtenerOpciones().size());
    }

    @Test
    public void FlujoNormalDePreguntaOrderedChoiceConRespuestaOrdenadaOtorga1Punto() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");


        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(0));
        opcionesElegidas.add(opciones.get(1));
        opcionesElegidas.add(opciones.get(2));
        opcionesElegidas.add(opciones.get(3));
        opcionesElegidas.add(opciones.get(4));

        assertEquals(1, pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor());
    }

    @Test
    public void FlujoNormalDePreguntaOrderedChoiceConRespuestaDesordenadaNoOtorgaPuntos() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        // Se presenta otra combinacion de opciones distinta a la correcta.
        opcionesElegidas.add(opciones.get(0));
        opcionesElegidas.add(opciones.get(1));
        opcionesElegidas.add(opciones.get(2));
        opcionesElegidas.add(opciones.get(4));
        opcionesElegidas.add(opciones.get(3));

        assertEquals(0, pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor());
    }

    @Test
    public void ConfirmarUnaRespuestaOrdenadaPeroSinIncluirTodosLosElementosNoOtorgaPuntos() throws PreguntaError, RespuestaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        // Se presenta una combinacion incompleta
        opcionesElegidas.add(opciones.get(0));
        opcionesElegidas.add(opciones.get(1));
        opcionesElegidas.add(opciones.get(2));

        assertEquals(0, pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor());
    }

    @Test
    public void AgregarMasDeCincoOpcionesALaPreguntaLanzaExcepcion() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");

        assertThrows(PreguntaError.class, () -> pregunta.agregarOpcion("Seis"));
    }

    @Test
    public void ObtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        assertEquals(0, pregunta.obtenerOpciones().size());

        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        assertEquals(2, pregunta.obtenerOpciones().size());
    }

}
