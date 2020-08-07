package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntoNuloTest {

    // obtenerValor
    @Test
    public void obtenerValorDevuelveCero() {
        PuntoNulo punto = new PuntoNulo();

        assertEquals(0, punto.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor() {
        PuntoNulo punto = new PuntoNulo();

        Punto copia = punto.copiar();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntoNegativoConElMismoValor() {
        PuntoNulo punto = new PuntoNulo();

        PuntoNulo copia = punto.clone();

        assertEquals(punto.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConFactorNegativoDevuelvePuntoConValorCero() {
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorCeroDevuelvePuntoConValorCero() {
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorDosDevuelveUnPuntoConElDobleDelValor() {
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(2);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConFactorGrandeDevuelveLoCorrecto() {
        PuntoNulo puntoNulo = new PuntoNulo();

        Punto punto = puntoNulo.multiplicarPorFactor(25);

        assertEquals(0, punto.obtenerValor());
    }
}
