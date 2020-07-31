package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerdaderoFalsoConPenalidadTest {

    @Test
    public void VerdaderoFalsoPuedeCrearseIndicandolecualEsLaRespuestaCorrecta() {
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

        assertEquals(respuesta.obtenerPuntaje().getValor(),1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeIncorrectamente() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        Jugador jugador = new Jugador("Paula");
        pregunta.iniciar(jugador);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        pregunta.seleccionarOpcion(opciones.get(1));
        Respuesta respuesta = pregunta.confirmar();

        assertEquals(respuesta.obtenerPuntaje().getValor(),-1);
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

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(respuestaPaula);
        respuestas.add(respuestaMarta);

        assertEquals(respuestas.get(0).obtenerPuntaje().getValor(),1);
        assertEquals(respuestas.get(1).obtenerPuntaje().getValor(),-1);
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

        assertThrows(PreguntaError.class, pregunta::confirmar);

    }

    @Test
    public void CreacionVerdaderoFalsoObtenerTituloDevuelveElTitulo(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        assertEquals("¿Estamos en el año 2020?",pregunta.obtenerTitulo());
    }

    @Test
    public void OpcionVerdaderoFalsoMarcadaComoCorrectaValeUnoYFalsoMenosUno(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Verdadero",opciones.get(0).obtenerTitulo());
        assertEquals(1,opciones.get(0).obtenerPunto().getValor());

        assertEquals("Falso",opciones.get(1).obtenerTitulo());
        assertEquals(-1,opciones.get(1).obtenerPunto().getValor());
    }

    @Test
    public void OpcionVerdaderoMarcadaComoIncorrectaValeMenosUnoYFalsoUno(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?",false);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Verdadero",opciones.get(0).obtenerTitulo());
        assertEquals(-1,opciones.get(0).obtenerPunto().getValor());

        assertEquals("Falso",opciones.get(1).obtenerTitulo());
        assertEquals(1,opciones.get(1).obtenerPunto().getValor());

    }

    @Test
    public void JugadorRespondePeroNoConfirmaOtroJugadorNoPuedeResponder() throws PreguntaError, RespuestaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?",false);

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador pedro = new Jugador("Pedro");
        Jugador marcos = new Jugador("Marcos");

        pregunta.iniciar(pedro);
        pregunta.seleccionarOpcion(opciones.get(0));

        assertThrows(PreguntaError.class, ()-> pregunta.iniciar(marcos));

    }

    @Test
    public void IniciarPreguntaSinJugadorLanzaExcepcion() {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        assertThrows(PreguntaError.class, ()->pregunta.iniciar(null));
    }

    @Test
    public void IniciarDosVecesSinConfirmarLanzaExcepcion() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);
        Jugador jugador = new Jugador("Paula");

        pregunta.iniciar(jugador);

        assertThrows(PreguntaError.class, ()-> pregunta.iniciar(jugador));
    }

    @Test
    public void AlCrearseLaPreguntaPrimeroSeGuardaLaOpcionVerdaderoYDespuesFalsoSinImportarCualEsLaCorrecta(){
        VerdaderoFalsoConPenalidad pregunta1 = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);

        ArrayList<Opcion> opciones1 = pregunta1.obtenerOpciones();

        assertEquals("Verdadero", opciones1.get(0).obtenerTitulo());
        assertEquals("Falso", opciones1.get(1).obtenerTitulo());

        VerdaderoFalsoConPenalidad pregunta2 = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?",false);

        ArrayList<Opcion> opciones2 = pregunta2.obtenerOpciones();

        assertEquals("Verdadero", opciones2.get(0).obtenerTitulo());
        assertEquals("Falso", opciones2.get(1).obtenerTitulo());
    }

    @Test
    public void SoloSePuedeIniciarUnaVezSinConfirmar() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?",false);
        Jugador jugador = new Jugador("Carlos");
        pregunta.iniciar(jugador);

        assertThrows(PreguntaError.class, ()-> pregunta.iniciar(jugador));
    }

    @Test
    public void NoSePuedeSeleccionarUnaOpcionSinHaberIniciado(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?",true);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, ()->pregunta.seleccionarOpcion(opciones.get(1)));
    }

}
