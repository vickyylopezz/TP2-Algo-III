package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerdaderoFalsoConPenalidadTest {

    @Test
    public void VerdaderoFalsoPuedeCrearseIndicandolecualEsLaRespuestaCorrecta() {
        VerdaderoFalsoConPenalidad pregunta1 = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        assertEquals(pregunta1.obtenerOpciones().size(),2);

        VerdaderoFalsoConPenalidad pregunta2 = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Falso",
                "Verdadero"
        );

        assertEquals(pregunta2.obtenerOpciones().size(),2);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeCorrectamente() throws PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(0));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor(),1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeIncorrectamente() throws PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Falso",
                "Verdadero"
        );

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(1));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor(),-1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadores() throws PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidasJugador1 = new ArrayList<>();
        opcionesElegidasJugador1.add(opciones.get(1));

        ArrayList<Opcion> opcionesElegidasJugador2 = new ArrayList<>();
        opcionesElegidasJugador2.add(opciones.get(0));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidasJugador1).obtenerValor(),-1);
        assertEquals(pregunta.puntajeConOpciones(opcionesElegidasJugador2).obtenerValor(),1);
    }

    @Test
    public void CreacionVerdaderoFalsoObtenerTituloDevuelveElTitulo(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        assertEquals("¿Estamos en el año 2020?",pregunta.obtenerTitulo());
    }

    @Test
    public void OpcionVerdaderoFalsoMarcadaComoCorrectaValeUnoYFalsoMenosUno(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Verdadero",opciones.get(0).obtenerTitulo());
        assertEquals(1,opciones.get(0).obtenerPunto().obtenerValor());

        assertEquals("Falso",opciones.get(1).obtenerTitulo());
        assertEquals(-1,opciones.get(1).obtenerPunto().obtenerValor());
    }

    @Test
    public void OpcionVerdaderoMarcadaComoIncorrectaValeMenosUnoYFalsoUno(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Falso",
                "Verdadero"
        );

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Verdadero",opciones.get(1).obtenerTitulo());
        assertEquals(-1,opciones.get(1).obtenerPunto().obtenerValor());

        assertEquals("Falso",opciones.get(0).obtenerTitulo());
        assertEquals(1,opciones.get(0).obtenerPunto().obtenerValor());

    }

    @Test
    public void VerdaderoFalsoConPenalidadDevuelveTrueEnPenalidad(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        assertTrue(pregunta.conPenalidad());
    }

}
