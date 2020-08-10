package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VerdaderoFalsoClasicoTest {

    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertEquals("¿Estamos en Algoritmos y programcion 3?", preguntavf.obtenerTitulo());
        assertEquals(2, preguntavf.obtenerOpciones().size());
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PreguntaError, PuntoError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        ArrayList<Opcion> opciones = preguntavf.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidasJugador1 = new ArrayList<>();
        opcionesElegidasJugador1.add(opciones.get(0));

        ArrayList<Opcion> opcionesElegidasJugador2 = new ArrayList<>();
        opcionesElegidasJugador1.add(opciones.get(1));

        Integer esperadoJugador1 = 1;
        Integer esperadoJugador2 = 0;

        assertEquals(esperadoJugador1, preguntavf.puntajeConOpciones(opcionesElegidasJugador1).obtenerPunto().obtenerValor());
        assertEquals(esperadoJugador2, preguntavf.puntajeConOpciones(opcionesElegidasJugador2).obtenerPunto().obtenerValor());
    }

    @Test
    public void AgregarDosOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionCorrecta("Falso"));
    }

    @Test
    public void AgregarDosOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionIncorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionIncorrecta("Falso"));
    }

    @Test
    public void AgregarUnaTerceraOpcionLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        preguntavf.agregarOpcionCorrecta("Verdadero");
        preguntavf.agregarOpcionIncorrecta("Falso");

        assertThrows(PreguntaError.class, () -> preguntavf.agregarOpcionCorrecta("True"));
    }

    @Test
    public void ObtenerPuntajeConOpcionesDeUnArregloVacioDevuelveCero() throws PuntoError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico("¿Estamos en Algoritmos y programcion 3?");
        ArrayList<Opcion> opciones = new ArrayList<>();

        assertEquals(0, preguntavf.puntajeConOpciones(opciones).obtenerPunto().obtenerValor());
    }

}

