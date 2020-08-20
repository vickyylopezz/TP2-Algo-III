package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoClasico;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerdaderoFalsoClasicoTest {

    @Test
    public void CreacionDeVerdaderoFalsoIndicandoRespuestaCorrecta() {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico(
                "¿Estamos en Algoritmos y programcion 3?",
                "Verdadero",
                "Falso"
        );

        assertEquals("¿Estamos en Algoritmos y programcion 3?", preguntavf.obtenerTitulo());
        assertEquals(2, preguntavf.obtenerOpciones().size());
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosCorrectamenteAUnaListaDeRespuestas() throws PuntoError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico(
                "¿Estamos en Algoritmos y programcion 3?",
                "Verdadero",
                "Falso"
        );

        ArrayList<Opcion> opciones = preguntavf.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidasJugador1 = new ArrayList<>();
        opcionesElegidasJugador1.add(opciones.get(0));

        ArrayList<Opcion> opcionesElegidasJugador2 = new ArrayList<>();
        opcionesElegidasJugador1.add(opciones.get(1));

        Integer esperadoJugador1 = 1;
        Integer esperadoJugador2 = 0;

        assertEquals(esperadoJugador1, preguntavf.puntajeConOpciones(opcionesElegidasJugador1).obtenerValor());
        assertEquals(esperadoJugador2, preguntavf.puntajeConOpciones(opcionesElegidasJugador2).obtenerValor());
    }

    @Test
    public void ObtenerPuntajeConOpcionesDeUnArregloVacioDevuelveCero() throws PuntoError {
        VerdaderoFalsoClasico preguntavf = new VerdaderoFalsoClasico(
                "¿Estamos en Algoritmos y programcion 3?",
                "Verdader",
                "Falso"
        );
        ArrayList<Opcion> opciones = new ArrayList<>();

        assertEquals(0, preguntavf.puntajeConOpciones(opciones).obtenerValor());
    }
}

