package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerdaderoFalsoConPenalidadTest {

    @Test
    public void VerdaderoFalsoPuedeCrearseIndicándolecualEsLaRespuestaCorrecta() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta1 = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        assertEquals(pregunta1.obtenerOpciones().size(),2);

        VerdaderoFalsoConPenalidad pregunta2 = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",false);

        assertEquals(pregunta2.obtenerOpciones().size(),2);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeCorrectamente() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        Jugador jugador = new Jugador("Paula");
        pregunta.iniciar(jugador);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        pregunta.seleccionarOpcion(opciones.get(0));
        Respuesta respuesta = pregunta.confirmar();

        assertEquals(respuesta.puntaje(),1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeIncorrectamente() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        Jugador jugador = new Jugador("Paula");
        pregunta.iniciar(jugador);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        pregunta.seleccionarOpcion(opciones.get(1));
        Respuesta respuesta = pregunta.confirmar();

        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuesta);

        ArrayList<Integer> puntajes = new ArrayList<Integer>();
        puntajes = pregunta.puntajeConRespuestas(respuestas);
        assertEquals(puntajes.get(0),-1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadores() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador paula = new Jugador("Paula");
        pregunta.iniciar(paula);
        pregunta.seleccionarOpcion(opciones.get(0));
        Respuesta respuestaPaula = pregunta.confirmar();

        Jugador marta = new Jugador("Marta");
        pregunta.iniciar(marta);
        pregunta.seleccionarOpcion(opciones.get(1));
        Respuesta respuestaMarta = pregunta.confirmar();

        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        respuestas.add(respuestaPaula);
        respuestas.add(respuestaMarta);

        ArrayList<Integer> puntajes = new ArrayList<Integer>();
        puntajes = pregunta.puntajeConRespuestas(respuestas);
        assertEquals(puntajes.get(0),1);
        assertEquals(puntajes.get(1),-1);
    }

    @Test
    public void VerdaderoFalsoConPenalidadImplementaInterfacePregunta(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        assertEquals(true, pregunta instanceof Pregunta);
    }

    @Test
    public void UnJugadorIntentaElegirDosOpcionesDeVerdaderoFalsoYLanzaExcepcion() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",false);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador paula = new Jugador("Paula");
        pregunta.iniciar(paula);
        pregunta.seleccionarOpcion(opciones.get(0));

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(opciones.get(1)) );

    }

    @Test
    public void UnJugadorIntentaElegirDosVecesLaMismaOpcionLanzaExcepcion() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",false);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador paula = new Jugador("Paula");
        pregunta.iniciar(paula);
        pregunta.seleccionarOpcion(opciones.get(0));

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(opciones.get(1)) );

    }

    @Test
    public void IntentarConfirmarUnaPreguntaSinIniciarlaLanzaExcepcion(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",false);

        assertThrows(PreguntaError.class, ()-> pregunta.confirmar() );

    }
}
