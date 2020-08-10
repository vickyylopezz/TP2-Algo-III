package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VerdaderoFalsoConPenalidadTest {

    @Test
    public void VerdaderoFalsoPuedeCrearseIndicandolecualEsLaRespuestaCorrecta() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta1 = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?");
        pregunta1.agregarOpcionCorrecta("Verdadero");
        pregunta1.agregarOpcionIncorrecta("Falso");

        ArrayList<Opcion> opciones = pregunta1.obtenerOpciones();
        Integer prueba = opciones.size();
        assertEquals(pregunta1.obtenerOpciones().size(),2);

        VerdaderoFalsoConPenalidad pregunta2 = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?");
        pregunta2.agregarOpcionCorrecta("Falso");
        pregunta2.agregarOpcionIncorrecta("Verdadero");

        assertEquals(pregunta2.obtenerOpciones().size(),2);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeCorrectamente() throws PreguntaError, PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?");
        pregunta.agregarOpcionCorrecta("Verdadero");
        pregunta.agregarOpcionIncorrecta("Falso");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(0));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerPunto().obtenerValor(),1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadorQueRespondeIncorrectamente() throws PreguntaError, PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?");
        pregunta.agregarOpcionCorrecta("Falso");
        pregunta.agregarOpcionIncorrecta("Verdadero");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(1));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerPunto().obtenerValor(),-1);
    }

    @Test
    public void VerdaderoFalsoAsignaPuntosAJugadores() throws PreguntaError, PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?");
        pregunta.agregarOpcionCorrecta("Verdadero");
        pregunta.agregarOpcionIncorrecta("Falso");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidasJugador1 = new ArrayList<>();
        opcionesElegidasJugador1.add(opciones.get(1));

        ArrayList<Opcion> opcionesElegidasJugador2 = new ArrayList<>();
        opcionesElegidasJugador2.add(opciones.get(0));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidasJugador1).obtenerPunto().obtenerValor(),-1);
        assertEquals(pregunta.puntajeConOpciones(opcionesElegidasJugador2).obtenerPunto().obtenerValor(),1);
    }

    @Test
    public void CreacionVerdaderoFalsoObtenerTituloDevuelveElTitulo(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?");

        assertEquals("¿Estamos en el año 2020?",pregunta.obtenerTitulo());
    }

    @Test
    public void OpcionVerdaderoFalsoMarcadaComoCorrectaValeUnoYFalsoMenosUno() throws PreguntaError, PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2020?");
        pregunta.agregarOpcionCorrecta("Verdadero");
        pregunta.agregarOpcionIncorrecta("Falso");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Verdadero",opciones.get(0).obtenerTitulo());
        assertEquals(1,opciones.get(0).obtenerPunto().obtenerValor());

        assertEquals("Falso",opciones.get(1).obtenerTitulo());
        assertEquals(-1,opciones.get(1).obtenerPunto().obtenerValor());
    }

    @Test
    public void OpcionVerdaderoMarcadaComoIncorrectaValeMenosUnoYFalsoUno() throws PreguntaError, PuntoError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?");
        pregunta.agregarOpcionCorrecta("Falso");
        pregunta.agregarOpcionIncorrecta("Verdadero");
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Verdadero",opciones.get(1).obtenerTitulo());
        assertEquals(-1,opciones.get(1).obtenerPunto().obtenerValor());

        assertEquals("Falso",opciones.get(0).obtenerTitulo());
        assertEquals(1,opciones.get(0).obtenerPunto().obtenerValor());

    }

    @Test
    public void AgregarDosOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?");
        pregunta.agregarOpcionCorrecta("Falso");

        assertThrows(PreguntaError.class, () -> pregunta.agregarOpcionCorrecta("False"));
    }

    @Test
    public void AgregarDosOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("¿Estamos en el año 2021?");
        pregunta.agregarOpcionIncorrecta("Verdadero");

        assertThrows(PreguntaError.class, () -> pregunta.agregarOpcionIncorrecta("True"));
    }

    @Test
    public void VerdaderoFalsoConPenalidadDevuelveTrueEnPenalidad(){
        VerdaderoFalsoConPenalidad pregunta = new VerdaderoFalsoConPenalidad("Estamos el algoritmos?");

        assertTrue(pregunta.conPenalidad());
    }

}
