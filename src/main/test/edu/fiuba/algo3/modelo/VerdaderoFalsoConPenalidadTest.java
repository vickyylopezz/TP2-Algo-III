package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerdaderoFalsoConPenalidadTest {

    @Test
    public void VerdaderoFalsoPuedeCrearseIndicándolecualEsLaRespuestaCorrecta() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        assertEquals(pregunta.obtenerOpciones().size(),2);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeCorrectamente() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        Jugador jugador = new Jugador("Paula");
        pregunta.iniciar(jugador);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        pregunta.seleccionarOpcion(opciones.get(0));
        Respuesta respuesta = pregunta.confirmar();

        assertEquals(respuesta.puntaje(),1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeIncorrectamente() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        Jugador jugador = new Jugador("Paula");
        pregunta.iniciar(jugador);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        pregunta.seleccionarOpcion(opciones.get(1));
        Respuesta respuesta = pregunta.confirmar();

        assertEquals(respuesta.puntaje(),-1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadores() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();
        Jugador paula = new Jugador("Paula");
        pregunta.iniciar(paula);
        pregunta.seleccionarOpcion(opciones.get(0));
        respuestas.add(pregunta.confirmar());


        Jugador marta = new Jugador("Marta");
        pregunta.iniciar(marta);
        pregunta.seleccionarOpcion(opciones.get(1));
        respuestas.add(pregunta.confirmar());

        assertEquals(respuestas.get(0).puntaje(),1);
        assertEquals(respuestas.get(1).puntaje(),-1);
    }

    @Test
    public void VerdaderoFalsoConPenalidadImplementaInterfacePregunta(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        assertEquals(true, pregunta instanceof Pregunta);
    }

}
