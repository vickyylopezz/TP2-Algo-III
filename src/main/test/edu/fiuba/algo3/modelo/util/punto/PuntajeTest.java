package edu.fiuba.algo3.modelo.util.punto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntajeTest {

    // obtenerValor
    @Test
    public void obtenerValorSinAgregarPuntoEsCero(){
        PuntoExacto puntaje = new PuntoExacto();

        assertEquals(0, puntaje.obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoPuntoPositivoValorDevuelveUno(){
        PuntoExacto puntaje = new PuntoExacto();

        puntaje.agregarValor(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerValor());
    }

    @Test
    public void obtenerValorAgregandoVariosPuntosLosSuma(){
        PuntoExacto puntaje = new PuntoExacto();

        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());

        assertEquals(-1, puntaje.obtenerValor());
    }

    // agregarPunto

    @Test
    public void agregarPuntoPositivoAumentaElValor(){
        PuntoExacto puntaje = new PuntoExacto();

        puntaje.agregarValor(new PuntoPositivo());

        assertEquals(1, puntaje.obtenerValor());
    }

    // copiar
    @Test
    public void copiarDevuelveUnPuntoConElMismoValor(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());

        Punto copia = puntaje.copiar();

        assertEquals(puntaje.obtenerValor(), copia.obtenerValor());
    }

    // clone
    @Test
    public void cloneDevuelveUnPuntajeConElMismoValor(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());

        PuntoExacto copia = puntaje.clone();

        assertEquals(puntaje.obtenerValor(), copia.obtenerValor());
    }

    // multiplicarPorFactor
    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorNegativoDevuelvePuntoConValorCero(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorCeroDevuelvePuntoConValorCero(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorDosDevuelveUnPuntoConElDobleDelValor(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(2);

        assertEquals(4, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajePositivoConFactorGrandeDevuelveLoCorrecto(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoPositivo());

        Punto punto = puntaje.multiplicarPorFactor(25);

        assertEquals(50, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorNegativoDevuelvePuntoConValorCero(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(-4);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorCeroDevuelvePuntoConValorCero(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(0);

        assertEquals(0, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorDosDevuelveUnPuntoConElDobleDelValor(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(3);

        assertEquals(-9, punto.obtenerValor());
    }

    @Test
    public void multiplicarPorFactorConPuntajeNegativoConFactorGrandeDevuelveLoCorrecto(){
        PuntoExacto puntaje = new PuntoExacto();
        puntaje.agregarValor(new PuntoPositivo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNulo());
        puntaje.agregarValor(new PuntoNegativo());
        puntaje.agregarValor(new PuntoNegativo());

        Punto punto = puntaje.multiplicarPorFactor(30);

        assertEquals(-60, punto.obtenerValor());
    }
}
