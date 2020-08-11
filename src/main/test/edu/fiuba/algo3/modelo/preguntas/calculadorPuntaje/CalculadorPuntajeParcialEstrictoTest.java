package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculadorPuntajeParcialEstrictoTest {
    @Test
    public void calcularDevuelvePuntajeIncorrectoConDosOpcionesCorrectaYUnaIncorrecta() throws PuntoError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);
        Opcion opcion4 = mock(Opcion.class);

        when(opcion1.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion2.obtenerPunto()).thenReturn(new PuntoNegativo());
        when(opcion3.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion4.obtenerPunto()).thenReturn(new PuntoNegativo());

        ArrayList<Opcion> opcionesPregunta = new ArrayList<>();
        opcionesPregunta.add(opcion1);
        opcionesPregunta.add(opcion2);
        opcionesPregunta.add(opcion3);
        opcionesPregunta.add(opcion4);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opcionesPregunta);
        when(pregunta.puntajeCorrecto()).thenReturn(new PuntoPositivo());
        when(pregunta.puntajeIncorrecto()).thenReturn(new PuntoNegativo());

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        CalculadorPuntajeParcialEstricto calc = new CalculadorPuntajeParcialEstricto();

        Punto punto = calc.calcular(pregunta, opcionesSeleccionadas);

        assertEquals(-1, punto.obtenerValor());
    }

    @Test
    public void calcularDevuelvePuntajeUnoConUnaOpcionCorrectaSeleccionadaHabiendoDosCorrectas() throws PuntoError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);
        Opcion opcion4 = mock(Opcion.class);

        when(opcion1.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion2.obtenerPunto()).thenReturn(new PuntoNegativo());
        when(opcion3.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion4.obtenerPunto()).thenReturn(new PuntoNegativo());

        ArrayList<Opcion> opcionesPregunta = new ArrayList<>();
        opcionesPregunta.add(opcion1);
        opcionesPregunta.add(opcion2);
        opcionesPregunta.add(opcion3);
        opcionesPregunta.add(opcion4);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opcionesPregunta);
        when(pregunta.puntajeCorrecto()).thenReturn(new PuntoPositivo());
        when(pregunta.puntajeIncorrecto()).thenReturn(new PuntoNegativo());

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);

        CalculadorPuntajeParcialEstricto calc = new CalculadorPuntajeParcialEstricto();

        Punto punto = calc.calcular(pregunta, opcionesSeleccionadas);

        assertEquals(1, punto.obtenerPunto().obtenerValor());
    }

    @Test
    public void calcularDevuelvePuntajeIncorrectoSeleccionandoUnaOpcionIncorrecta() throws PuntoError {
        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);
        Opcion opcion4 = mock(Opcion.class);

        when(opcion1.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion2.obtenerPunto()).thenReturn(new PuntoNegativo());
        when(opcion3.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion4.obtenerPunto()).thenReturn(new PuntoNegativo());

        ArrayList<Opcion> opcionesPregunta = new ArrayList<>();
        opcionesPregunta.add(opcion1);
        opcionesPregunta.add(opcion2);
        opcionesPregunta.add(opcion3);
        opcionesPregunta.add(opcion4);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.obtenerOpciones()).thenReturn(opcionesPregunta);
        when(pregunta.puntajeCorrecto()).thenReturn(new PuntoPositivo());
        when(pregunta.puntajeIncorrecto()).thenReturn(new PuntoNegativo());

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion2);

        CalculadorPuntajeParcialEstricto calc = new CalculadorPuntajeParcialEstricto();

        Punto punto = calc.calcular(pregunta, opcionesSeleccionadas);

        assertEquals(-1, punto.obtenerValor());
    }
}
