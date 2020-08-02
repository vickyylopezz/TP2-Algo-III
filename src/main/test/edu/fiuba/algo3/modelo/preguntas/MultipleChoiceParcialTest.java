package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceParcial;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class MultipleChoiceParcialTest {

    @Test
    public void CreacionDeMultipleChoiseConSegundosNegativosLanzaPreguntaError(){
        assertThrows(PreguntaError.class, () -> new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?", -1));
    }

    @Test
    public void CreacionDeMultipleChoiceParcialIndicandoRespuestaCorrecta() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCP.titulo());
        assertEquals(4, preguntaMCP.obtenerOpciones().size());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void MultipleChoiceParcialAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PreguntaError, RespuestaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");

        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = preguntaMCP.obtenerOpciones();

        preguntaMCP.iniciar(jugador);
        preguntaMCP.seleccionarOpcion(opciones.get(0));
        preguntaMCP.seleccionarOpcion(opciones.get(1));
        Respuesta respuestaJugador1 = preguntaMCP.confirmar();

        preguntaMCP.iniciar(jugador);
        preguntaMCP.seleccionarOpcion(opciones.get(0));
        preguntaMCP.seleccionarOpcion(opciones.get(2));
        Respuesta respuestaJugador2 = preguntaMCP.confirmar();

        preguntaMCP.iniciar(jugador);
        preguntaMCP.seleccionarOpcion(opciones.get(1));
        Respuesta respuestaJugador3 = preguntaMCP.confirmar();

        Integer esperadoJugador1 = 2;
        Integer esperadoJugador2 = 0;
        Integer esperadoJugador3 = 1;

        assertEquals(esperadoJugador1, respuestaJugador1.obtenerPuntaje().getValor());
        assertEquals(esperadoJugador2, respuestaJugador2.obtenerPuntaje().getValor());
        assertEquals(esperadoJugador3, respuestaJugador3.obtenerPuntaje().getValor());
    }

    @Test
    public void AgregarMasDeCincoOpcionesLanzaUnPreguntaError() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCP.agregarOpcionCorrecta("Francisco");
        preguntaMCP.agregarOpcionCorrecta("Victoria");
        preguntaMCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCP.agregarOpcionIncorrecta("Catalina");
        preguntaMCP.agregarOpcionCorrecta("Juan");

        assertThrows(PreguntaError.class, () -> preguntaMCP.agregarOpcionIncorrecta("Nicanor"));
        assertThrows(PreguntaError.class, () -> preguntaMCP.agregarOpcionCorrecta("Celeste"));
    }

    @Test
    public void ObtenerPuntajeConOpcionesDeUnArregloVacioDevuelveCero() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Cuál es el apellido de nuestro corrector?", 15);
        ArrayList<Opcion> opciones = new ArrayList<Opcion>();

        assertEquals(0, preguntaMCP.puntajeConOpciones(opciones).getValor());
    }

    @Test
    public void IniciarMultipleChoiceParcialConMenosDeDosOpcionesLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Cuál es el apellido de nuestro corrector?", 15);
        preguntaMCP.agregarOpcionIncorrecta("Jirafales");
        Jugador jugador = mock(Jugador.class);

        assertThrows(PreguntaError.class, () -> preguntaMCP.iniciar(jugador));
    }

    @Test
    public void IniciarMultipleChoiceParcialSinUnJugadorLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Cuál es el apellido de nuestro corrector?", 15);
        preguntaMCP.agregarOpcionIncorrecta("Jirafales");
        preguntaMCP.agregarOpcionIncorrecta("Peña");
        preguntaMCP.agregarOpcionCorrecta("Rodríguez");

        assertThrows(PreguntaError.class, () -> preguntaMCP.iniciar(null));
    }

    @Test
    public void IniciarMultipleChoiceParcialSinConfirmarLaRespuestaAnteriorLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceParcial preguntaMCP = new MultipleChoiceParcial("¿Cuál es el apellido de nuestro corrector?", 15);
        preguntaMCP.agregarOpcionIncorrecta("Jirafales");
        preguntaMCP.agregarOpcionIncorrecta("Peña");
        preguntaMCP.agregarOpcionCorrecta("Rodríguez");
        Jugador jugador = mock(Jugador.class);
        preguntaMCP.iniciar(jugador);

        assertThrows(PreguntaError.class, () -> preguntaMCP.iniciar(jugador));
    }
}
