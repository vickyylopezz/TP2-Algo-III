package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.opcion.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VerdaderoFalsoClasicoTest {

    /*@Test
    public void CreacionVerdaderoFalsoConSegundosNegativosLanzaMultipleChoiceError() {
        assertThrows(PreguntaError.class, () -> new VerdaderoFalsoClasico("¿Estamos en Algo 3?", -1));
    }

    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertEquals("¿Estamos en Algoritmos y programcion 3?", preguntavf.titulo());
        assertEquals(2, preguntavf.obtenerOpciones().size());
    }
    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PreguntaError, RespuestaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = preguntavf.obtenerOpciones();

        preguntavf.iniciar(jugador);
        preguntavf.seleccionarOpcion(opciones.get(0));
        Respuesta respuestaJugador1 = preguntavf.confirmar();
        preguntavf.iniciar(jugador);
        preguntavf.seleccionarOpcion(opciones.get(1));
        Respuesta respuestaJugador2 = preguntavf.confirmar();

        Integer esperadoJugador1 = 1;
        Integer esperadoJugador2 = 0;

        assertEquals(esperadoJugador1, respuestaJugador1.obtenerPuntaje().getValor());
        assertEquals(esperadoJugador2, respuestaJugador2.obtenerPuntaje().getValor());
    }

    @Test
    public void AgregarDosOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionCorrecta("Falso"));
    }

    @Test
    public void AgregarDosOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionIncorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionIncorrecta("Falso"));
    }

    @Test
    public void AgregarUnaTerceraOpcionLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionCorrecta("True"));
    }

    @Test
    public void ObtenerPuntajeConOpcionesDeUnArregloVacioDevuelveCero() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        ArrayList<Opcion> opciones = new ArrayList<Opcion>();

        assertEquals(0, preguntavf.puntajeConOpciones(opciones).getValor());
    }

    @Test
    public void IniciarVerdaderoFalsoClasicoConUnaSolaOpcionLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        Jugador jugador = mock(Jugador.class);

        assertThrows(PreguntaError.class, () -> preguntavf.iniciar(jugador));
    }

    @Test
    public void IniciarVerdaderoFalsoClasicoSinUnJugadorLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertThrows(PreguntaError.class, () -> preguntavf.iniciar(null));
    }

    @Test
    public void IniciarVerdaderoFalsoClasicoSinConfirmarLaRespuestaDelAnteriorJugadorLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");
        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        preguntavf.iniciar(jugador1);
        assertThrows(PreguntaError.class, () -> preguntavf.iniciar(jugador2));
    }

    @Test
    public void AgregarDosVecesLaMismaOpcionALaRespuestaLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?", 15);
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");
        Jugador jugador = mock(Jugador.class);
        ArrayList<Opcion> opciones = preguntavf.obtenerOpciones();

        preguntavf.iniciar(jugador);
        preguntavf.seleccionarOpcion(opciones.get(0));
        assertThrows(PreguntaError.class, () -> preguntavf.seleccionarOpcion(opciones.get(0)));
    }*/
}

