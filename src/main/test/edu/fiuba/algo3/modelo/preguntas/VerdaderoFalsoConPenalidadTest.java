package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerdaderoFalsoConPenalidadTest {

    @Test
    public void VerdaderoFalsoPuedeCrearseIndicandolecualEsLaRespuestaCorrecta() {
        VerdaderoFalso pregunta1 = VerdaderoFalso.ConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        assertEquals(pregunta1.obtenerOpciones().size(),2);

        VerdaderoFalso pregunta2 = VerdaderoFalso.ConPenalidad(
                "¿Estamos en el año 2020?",
                "Falso",
                "Verdadero"
        );

        assertEquals(pregunta2.obtenerOpciones().size(),2);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeCorrectamente() {
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
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
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeIncorrectamente() {
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
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
    public void VerdaderoFalsoAsignaPuntosAJugadores() {
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
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
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        assertEquals("¿Estamos en el año 2020?",pregunta.obtenerTitulo());
    }

    @Test
    public void OpcionVerdaderoFalsoMarcadaComoCorrectaValeUnoYFalsoMenosUno(){
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
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
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
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
        VerdaderoFalso pregunta = VerdaderoFalso.ConPenalidad(
                "¿Estamos en el año 2020?",
                "Verdadero",
                "Falso"
        );

        assertTrue(pregunta.conPenalidad());
    }

}
