package edu.fiuba.algo3.modelo.util.punto;

import edu.fiuba.algo3.modelo.excepciones.punto.PuntajeIgualError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuntoNuloTest {

    // obtenerValor
    @Test
    public void obtenerValorDevuelveCero() {
        PuntoNulo punto = new PuntoNulo();

        assertEquals(0, punto.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor(){
        PuntoNulo punto = new PuntoNulo();

        Punto copia = punto.copiar();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntoNegativoConElMismoValor(){
        PuntoNulo punto = new PuntoNulo();

        PuntoNulo copia = punto.clone();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConFactorNegativoDevuelvePuntoConValorCero(){
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorCeroDevuelvePuntoConValorCero(){
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorDosDevuelveUnPuntoConElDobleDelValor(){
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(2);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorGrandeDevuelveLoCorrecto(){
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(25);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void mayorDevuelveTrueSiElPuntajeEsMayorAlRecibido() throws PuntoError {
        PuntoNulo puntoNulo = new PuntoNulo();

        assertTrue(puntoNulo.mayor(new PuntoNegativo()));
    }

    @Test
    public void mayorDevuelveFalseSiElPuntajeEsMenorAlRecibido() throws PuntoError {
        PuntoNulo puntoNulo = new PuntoNulo();

        assertFalse(puntoNulo.mayor(new PuntoPositivo()));
    }

    @Test
    public void mayorLanzaExcepcionSiElPuntajeEsIgualAlRecibido() {
        PuntoNulo puntaje = new PuntoNulo();

        assertThrows(PuntajeIgualError.class, ()->puntaje.mayor(new PuntoNulo()));
    }
}
