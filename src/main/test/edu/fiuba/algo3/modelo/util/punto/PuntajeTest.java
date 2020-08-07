package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntajeTest {

    // obtenerValor
    @Test
    public void obtenerValorSinAgregarPuntoEsCero() {
        Puntaje puntaje = new Puntaje();

        assertEquals(0, puntaje.obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoPuntoPositivoValorDevuelveUno() {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoVariosPuntosLosSuma() {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        assertEquals(-1, puntaje.obtenerValor());
    }

    // agregarPunto
    @Test
    public void agregarPuntoNuloNoHaceNada() {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(null);

        assertEquals(0, puntaje.obtenerValor());
    }

    @Test
    public void agregarPuntoPositivoAumentaElValor() {
        Puntaje puntaje = new Puntaje();

        puntaje.agregarPunto(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto copia = puntaje.copiar();

        assertEquals(puntaje.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntajeConElMismoValor() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Puntaje copia = puntaje.clone();

        assertEquals(puntaje.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorNegativoDevuelvePuntoConValorCero() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorCeroDevuelvePuntoConValorCero() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorDosDevuelveUnPuntoConElDobleDelValor() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(2);

        assertEquals(4, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorGrandeDevuelveLoCorrecto() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(25);

        assertEquals(50, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorNegativoDevuelvePuntoConValorCero() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorCeroDevuelvePuntoConValorCero() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorDosDevuelveUnPuntoConElDobleDelValor() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(3);

        assertEquals(-9, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorGrandeDevuelveLoCorrecto() {
        Puntaje puntaje = new Puntaje();
        puntaje.agregarPunto(new PuntoPositivo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNulo());
        puntaje.agregarPunto(new PuntoNegativo());
        puntaje.agregarPunto(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(30);

        assertEquals(-60, punto.obtenerValor());
    }
}
