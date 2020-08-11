package edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculadorPuntajeClasicoTest {
    @Test
    public void calcularDevuelvePuntajeIncorrectoSiHayUnaOpcionIcorrecta() throws PuntoError {
        Punto puntoCorrecto = new PuntoPositivo();
        Punto puntoIncorreco = new PuntoNulo();

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);
        Opcion opcion4 = mock(Opcion.class);

        when(opcion1.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion2.obtenerPunto()).thenReturn(new PuntoNulo());
        when(opcion3.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion4.obtenerPunto()).thenReturn(new PuntoNulo());

        ArrayList<Opcion> opcionesPregunta = new ArrayList<>();
        opcionesPregunta.add(opcion1);
        opcionesPregunta.add(opcion2);
        opcionesPregunta.add(opcion3);
        opcionesPregunta.add(opcion4);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.cantidadOpcionesCorrectas()).thenReturn(2);
        when(pregunta.obtenerOpciones()).thenReturn(opcionesPregunta);
        when(pregunta.puntajeCorrecto()).thenReturn(puntoCorrecto);
        when(pregunta.puntajeIncorrecto()).thenReturn(puntoIncorreco);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion2);
        opcionesSeleccionadas.add(opcion3);

        CalculadorPuntajeClasico calc = new CalculadorPuntajeClasico();

        Punto punto = calc.calcular(pregunta, opcionesSeleccionadas);

        assertEquals(punto.obtenerValor(), puntoIncorreco.obtenerValor());
    }

    @Test
    public void calcularDevuelvePuntajeIncorrectoSiNoSeSeleccionaronTodasLasCorrectas() throws PuntoError {
        Punto puntoCorrecto = new PuntoPositivo();
        Punto puntoIncorreco = new PuntoNulo();

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);
        Opcion opcion4 = mock(Opcion.class);

        when(opcion1.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion2.obtenerPunto()).thenReturn(new PuntoNulo());
        when(opcion3.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion4.obtenerPunto()).thenReturn(new PuntoNulo());

        ArrayList<Opcion> opcionesPregunta = new ArrayList<>();
        opcionesPregunta.add(opcion1);
        opcionesPregunta.add(opcion2);
        opcionesPregunta.add(opcion3);
        opcionesPregunta.add(opcion4);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.cantidadOpcionesCorrectas()).thenReturn(2);
        when(pregunta.obtenerOpciones()).thenReturn(opcionesPregunta);
        when(pregunta.puntajeCorrecto()).thenReturn(puntoCorrecto);
        when(pregunta.puntajeIncorrecto()).thenReturn(puntoIncorreco);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);

        CalculadorPuntajeClasico calc = new CalculadorPuntajeClasico();

        Punto punto = calc.calcular(pregunta, opcionesSeleccionadas);

        assertEquals(punto.obtenerValor(), puntoIncorreco.obtenerValor());
    }

    @Test
    public void calcularDevuelvePuntajeSeleccionandoTodasLasCorrectasDevuelveElPuntajeCorrecto() throws PuntoError {
        Punto puntoCorrecto = new PuntoPositivo();
        Punto puntoIncorreco = new PuntoNulo();

        Opcion opcion1 = mock(Opcion.class);
        Opcion opcion2 = mock(Opcion.class);
        Opcion opcion3 = mock(Opcion.class);
        Opcion opcion4 = mock(Opcion.class);

        when(opcion1.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion2.obtenerPunto()).thenReturn(new PuntoNulo());
        when(opcion3.obtenerPunto()).thenReturn(new PuntoPositivo());
        when(opcion4.obtenerPunto()).thenReturn(new PuntoNulo());

        ArrayList<Opcion> opcionesPregunta = new ArrayList<>();
        opcionesPregunta.add(opcion1);
        opcionesPregunta.add(opcion2);
        opcionesPregunta.add(opcion3);
        opcionesPregunta.add(opcion4);

        Pregunta pregunta = mock(Pregunta.class);
        when(pregunta.cantidadOpcionesCorrectas()).thenReturn(2);
        when(pregunta.obtenerOpciones()).thenReturn(opcionesPregunta);
        when(pregunta.puntajeCorrecto()).thenReturn(puntoCorrecto);
        when(pregunta.puntajeIncorrecto()).thenReturn(puntoIncorreco);

        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        opcionesSeleccionadas.add(opcion1);
        opcionesSeleccionadas.add(opcion3);

        CalculadorPuntajeClasico calc = new CalculadorPuntajeClasico();

        Punto punto = calc.calcular(pregunta, opcionesSeleccionadas);

        assertEquals(punto.obtenerValor(), puntoCorrecto.obtenerValor());
    }
}
