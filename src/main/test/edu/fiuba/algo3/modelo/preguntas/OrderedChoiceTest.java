package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.orderedChoice.OrderedChoice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OrderedChoiceTest {
    /*
    @Test
    public void CreacionDePreguntaOrderedChoice() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");

        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        // La respuesta correcta estará compuesta por las opciones en el orden en que se agregaron.

        assertEquals("Ordenar de menor a mayor", pregunta.titulo());
        assertEquals(3, pregunta.obtenerOpciones().size());
    }

    @Test
    public void FlujoNormalDePreguntaOrderedChoiceConRespuestaOrdenadaOtorga1Punto() throws PreguntaError, RespuestaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");

        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        // El metodo iniciar instancia una respuestaActual dentro de la pregunta,
        // a la cual se le van agregando las elecciones del jugador con seleccionarOpcion.
        pregunta.iniciar(jugador);

        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(1));
        pregunta.seleccionarOpcion(opciones.get(2));
        pregunta.seleccionarOpcion(opciones.get(3));
        pregunta.seleccionarOpcion(opciones.get(4));

        // El metodo confirmar devuelve la respuestaActual como parametro y se deshace de la referencia.
        // Asi se puede volver a usar la pregunta con otros jugadores.
        Respuesta respuestaJugador1 = pregunta.confirmar();

        assertEquals(1, respuestaJugador1.obtenerPuntaje().getValor());
    }

    @Test
    public void FlujoNormalDePreguntaOrderedChoiceConRespuestaDesordenadaNoOtorgaPuntos() throws PreguntaError, RespuestaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");

        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        pregunta.iniciar(jugador);

        // Se presenta otra combinacion de opciones distinta a la correcta.
        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(1));
        pregunta.seleccionarOpcion(opciones.get(2));
        pregunta.seleccionarOpcion(opciones.get(4));
        pregunta.seleccionarOpcion(opciones.get(3));

        Respuesta respuestaJugador1 = pregunta.confirmar();

        assertEquals(0, respuestaJugador1.obtenerPuntaje().getValor());
    }

    @Test
    public void ConfirmarUnaRespuestaOrdenadaPeroSinIncluirTodosLosElementosNoOtorgaPuntos() throws PreguntaError, RespuestaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Cuatro");
        pregunta.agregarOpcion("Cinco");

        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        pregunta.iniciar(jugador);

        // Se presenta una combinacion incompleta
        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(1));
        pregunta.seleccionarOpcion(opciones.get(2));

        Respuesta respuestaJugador1 = pregunta.confirmar();

        assertEquals(0, respuestaJugador1.obtenerPuntaje().getValor());
    }

    @Test
    public void SeleccionarUnaMismaOpcionMasDeUnaVezLanzaExcepcion() throws PreguntaError, RespuestaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Tres");

        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        pregunta.iniciar(jugador);

        // Se repite una de las opciones en la combinacion
        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(1));
        assertThrows(RespuestaError.class, () -> pregunta.seleccionarOpcion(opciones.get(1)));
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

    @Test
    public void IniciarPreguntaConMenosDeDosOpcionesLanzaExcepcion() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        Jugador jugador = mock(Jugador.class);

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(jugador));
    }

    @Test
    public void IniciarPreguntaSinUnJugadorLanzaExcepcion() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(null));
    }

    @Test
    public void IniciarDosVecesLaPreguntaSinHaberConfirmadoUnaRespuestaLanzaExcepcion() throws PreguntaError {
        OrderedChoice pregunta = new OrderedChoice("Ordenar de menor a mayor");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Dos");

        Jugador jugador = mock(Jugador.class);
        pregunta.iniciar(jugador);

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(jugador));
    }
    */
}
