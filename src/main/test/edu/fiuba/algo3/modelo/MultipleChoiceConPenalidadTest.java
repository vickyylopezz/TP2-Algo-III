package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class MultipleChoiceConPenalidadTest {

    @Test
    public void CreacionDeMultipleChoiseConSegundosNegativosLanzaPreguntaError(){
        assertThrows(PreguntaError.class, () -> new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?", -1));
    }

    @Test
    public void CreacionDeMultipleChoiseParcialIndicandoRespuestaCorrecta() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");

        assertEquals("¿Quienes son integrantes del grupo PM3?", preguntaMCCP.titulo());
        assertEquals(4, preguntaMCCP.obtenerOpciones().size());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCCP.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void MultipleChoiseConPenalidadAsignaPuntosCorrectamenteADiferentesRespuestas() throws PreguntaError, RespuestaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?", 15);
        preguntaMCCP.agregarOpcionCorrecta("Francisco");
        preguntaMCCP.agregarOpcionCorrecta("Victoria");
        preguntaMCCP.agregarOpcionIncorrecta("Fernando");
        preguntaMCCP.agregarOpcionIncorrecta("Catalina");

        ArrayList<Opcion> opciones = preguntaMCCP.obtenerOpciones();
        Jugador jugador1 = new Jugador("Carlos");
        Jugador jugador2 = new Jugador("Julia");
        Jugador jugador3 = new Jugador("Ariel");
        Jugador jugador4 = new Jugador("Mabel");

        preguntaMCCP.iniciar(jugador1);
        preguntaMCCP.seleccionarOpcion(opciones.get(0));
        preguntaMCCP.seleccionarOpcion(opciones.get(1));
        Respuesta respuestaJugador1 = preguntaMCCP.confirmar();
        jugador1.sumarPuntaje(respuestaJugador1.obtenerPuntaje());

        preguntaMCCP.iniciar(jugador2);
        preguntaMCCP.seleccionarOpcion(opciones.get(0));
        preguntaMCCP.seleccionarOpcion(opciones.get(1));
        preguntaMCCP.seleccionarOpcion(opciones.get(2));
        Respuesta respuestaJugador2 = preguntaMCCP.confirmar();
        jugador2.sumarPuntaje(respuestaJugador2.obtenerPuntaje());

        preguntaMCCP.iniciar(jugador3);
        preguntaMCCP.seleccionarOpcion(opciones.get(1));
        Respuesta respuestaJugador3 = preguntaMCCP.confirmar();
        jugador3.sumarPuntaje(respuestaJugador3.obtenerPuntaje());

        preguntaMCCP.iniciar(jugador4);
        preguntaMCCP.seleccionarOpcion(opciones.get(2));
        preguntaMCCP.seleccionarOpcion(opciones.get(3));
        Respuesta respuestaJugador4 = preguntaMCCP.confirmar();
        jugador4.sumarPuntaje(respuestaJugador4.obtenerPuntaje());

        Integer esperadoJugador1 = 2;
        Integer esperadoJugador2 = 1;
        Integer esperadoJugador3 = 1;
        Integer esperadoJugador4 = -2;

        assertEquals(esperadoJugador1, respuestaJugador1.obtenerPuntaje().getValor());
        assertEquals(esperadoJugador2, respuestaJugador2.obtenerPuntaje().getValor());
        assertEquals(esperadoJugador3, respuestaJugador3.obtenerPuntaje().getValor());
        assertEquals(esperadoJugador4, respuestaJugador4.obtenerPuntaje().getValor());
    }

    @Test
    public void AgregarMasDeCincoOpcionesLanzaUnPreguntaError() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Quienes son integrantes del grupo PM3?", 15);
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
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Cuál es el apellido de nuestro corrector?", 15);
        ArrayList<Opcion> opciones = new ArrayList<Opcion>();

        assertEquals(0, preguntaMCCP.puntajeConOpciones(opciones).getValor());
    }

    @Test
    public void IniciarMultipleChoiceConPenalidadConMenosDeDosOpcionesLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Cuál es el apellido de nuestro corrector?", 15);
        preguntaMCCP.agregarOpcionIncorrecta("Jirafales");
        Jugador jugador = mock(Jugador.class);

        assertThrows(PreguntaError.class, () -> preguntaMCCP.iniciar(jugador));
    }

    @Test
    public void IniciarMultipleChoiceConPenalidadSinUnJugadorLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Cuál es el apellido de nuestro corrector?", 15);
        preguntaMCCP.agregarOpcionIncorrecta("Jirafales");
        preguntaMCCP.agregarOpcionIncorrecta("Peña");
        preguntaMCCP.agregarOpcionCorrecta("Rodríguez");

        assertThrows(PreguntaError.class, () -> preguntaMCCP.iniciar(null));
    }

    @Test
    public void IniciarMultipleChoiceConPenalidadSinConfirmarLaRespuestaAnteriorLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceConPenalidad preguntaMCCP = new MultipleChoiceConPenalidad("¿Cuál es el apellido de nuestro corrector?", 15);
        preguntaMCCP.agregarOpcionIncorrecta("Jirafales");
        preguntaMCCP.agregarOpcionIncorrecta("Peña");
        preguntaMCCP.agregarOpcionCorrecta("Rodríguez");
        Jugador jugador = mock(Jugador.class);
        preguntaMCCP.iniciar(jugador);

        assertThrows(PreguntaError.class, () -> preguntaMCCP.iniciar(jugador));
    }

}

